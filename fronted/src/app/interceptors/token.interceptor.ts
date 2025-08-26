import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from '../services/token.service';

const AUTH = 'Authorization';
const BEARER = 'Bearer ';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private tokenService:TokenService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
   let intReq = request;
    const token = this.tokenService.getToken();
   if(token != null){
      intReq = request.clone({
        headers: request.headers.set(AUTH, BEARER + token)
      });
   }
    return next.handle(intReq);
  }
}

