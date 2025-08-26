
import { FormBuilder, FormGroup } from '@angular/forms';



import { Component, OnInit } from '@angular/core';
import { Subscription, interval } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { InactivityService } from 'src/app/services/inactivity.service';
import { TokenService } from 'src/app/services/token.service';



@Component({
  selector: 'app-calculadora',
  templateUrl: './calculadora.component.html',
  styleUrls: ['./calculadora.component.scss'],


})
export class CalculadoraComponent implements OnInit{

  private updateIntervalMinutes = 3;
  private updateIntervalMillis = this.updateIntervalMinutes * 60 * 1000;
  private userActivitySubscription: Subscription | undefined;

  selectedvalue = 100000;
  formulario!: FormGroup;
  tasa= "10%"
  valueButton = 0;
  resultado = 0;
  plazoSeleccionado: number = 3; // Valor inicial del plazo seleccionado


  constructor(private fb: FormBuilder, private inactivityService: InactivityService,
    private tokenService: TokenService, private authService: AuthService) {
    // Configura el formulario reactivo
    this.formulario = this.fb.group({
      necesito: [this.selectedvalue],

    });
  }
  ngOnInit(): void {
    if (this.tokenService.isLoggued()) {
      // Actualizar token cada 3 minutos
      this.userActivitySubscription = interval(this.updateIntervalMillis).subscribe(() => {
        this.updateToken();
      });
      this.inactivityService.startInactivityTimer();
    }

  }

  calcularDatos(valueButton: any, selectedvalue:any){
    const tasa = 0.01;

    this.resultado=(selectedvalue * tasa) / (1 - Math.pow(1 + tasa, - valueButton));

    this.resultado = parseFloat(this.resultado.toFixed(0));


  }

  // Función para manejar el cambio de valor del slider
  onSliderChange(event: any) {
    this.selectedvalue = event.value;
    // Actualiza el valor en el formulario reactivo
    this.formulario.get('necesito')?.setValue(this.selectedvalue);
  }


  onSubmit(valueButton: any,selectedvalue:any) {



    switch (valueButton) {
      case 3:

      this.calcularDatos(valueButton,selectedvalue)
    //     console.log("Lunes");
    //     console.log(selectedvalue)
    // this.resultado=(selectedvalue * tasa)/ (1 - Math.pow(1 + tasa, -valueButton));
    // console.log(this.resultado)

    break;
  case 6:
    console.log("Martes");
    this.calcularDatos(valueButton,selectedvalue)

    break;
  case 12:
    console.log("Miércoles");
    this.calcularDatos(valueButton,selectedvalue)

    break;

  default:
    console.log("No es un día válido");

}


  }

  seleccionarPlazo(plazo: number) {
    this.plazoSeleccionado = plazo;
  }

  private updateToken(): void {
    this.authService.getCurrentSession().subscribe(
      updatedSession => {
        console.log("Token viejo: " + this.tokenService.getToken())
        this.tokenService.setToken(updatedSession.token);
        console.log("Token nuevo:" + this.tokenService.getToken());
      },
      error => {
        console.error('Error al actualizar el token:', error);
      }

    )
  }

  ngOnDestroy(): void {
    // Cancelar la suscripción cuando se destruye el componente
    if (this.userActivitySubscription) {
      this.userActivitySubscription.unsubscribe();
    }
  }

}
