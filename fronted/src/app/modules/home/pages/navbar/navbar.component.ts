import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit{

  rol:string = '';
  isLoggedIn: boolean = false;
  @ViewChild('menu') menu: ElementRef | undefined;
  isMenuOpen = false;

  constructor(private tokenService:TokenService, private router:Router,
    private renderer: Renderer2){}

  ngOnInit(): void {
    if(this.tokenService.isLoggued()){
      if(this.tokenService.getRol() === 'CUSTOMER'){
        this.rol = 'Cliente' ?? '';
      }else{
        this.rol = this.tokenService.getRol() ?? '';
      }


    }
  }



  isLoggedInUser():boolean{
    return this.tokenService.isLoggued();
  }

  SignOff(){
    this.tokenService.logOut();
    this.router.navigateByUrl("/auth/login");
  }

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
    const menuElement = this.menu?.nativeElement;
    if (this.isMenuOpen) {
      this.renderer.removeStyle(menuElement, 'display');
    } else {
      this.renderer.setStyle(menuElement, 'display', 'none');
    }
  }

}
