import { HttpInterceptorFn } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';

@Injectable()
export class AuthInterceptorService {
  constructor(private oauthService: OAuthService) { }
  
  getToken() {
    return this.oauthService.getAccessToken();
  }
}



export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const userToken = inject(AuthInterceptorService).getToken();
 // 'MY_TOKEN'; 
  const modifiedReq = req.clone({
    headers: req.headers.set('Authorization', `Bearer ${userToken}`),
  });

  return next(modifiedReq);
};
