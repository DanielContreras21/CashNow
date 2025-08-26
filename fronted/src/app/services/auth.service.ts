import { Injectable } from '@angular/core';
import { LoginRequest } from '../models/login-request';
import { Registro } from '../models/registro';

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, tap, throwError } from 'rxjs';
import { LoginResponse } from '../models/login-response';
import { environment } from 'src/environments/environment';




@Injectable({
  providedIn: 'root'
})
export class AuthService {

  urlAuth = environment.apiURL;

  userActivity: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private http:HttpClient){}

  login(request: LoginRequest):Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.urlAuth + 'login', request).pipe(
      tap( (response: LoginResponse) => {
        console.log("¡Se ha hecho clic en el botón de inicio de sesión!");
        this.currentUserLoginOn.next(true);
      }),
      catchError(this.handleError)
    );
  }
  //Actualiza token
  getCurrentSession():Observable<LoginResponse> {
    return this.http.get<LoginResponse>(this.urlAuth + 'current-session');


  }
  //Login moqueado
  loginMock(request:LoginRequest):Observable<any>{
    return this.http.get<any>('././assets/loginRequest.json').pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error:HttpErrorResponse){
    if(error.status === 0){
      console.error('Se ha producio un error ', error.error);
    }
    if(error.status === 409){
      console.error('El Usuario ya existe', error.error);
    }
    else{
      console.error('Backend retornó el código de estado ', error.status, error.error);
    }
    return throwError(()=> new Error('Algo falló. Por favor intente nuevamente.'));
  }


  registro(registro:Registro):Observable<any>{
    return this.http.post<any>(this.urlAuth + 'register', registro).pipe(
      catchError(this.handleError)
    );

  }

  get userLoginOn(): Observable<boolean>{
    return this.currentUserLoginOn.asObservable();
  }

}
