import { Injectable } from '@angular/core';
import { AuthConfig, OAuthLogger, OAuthService } from 'angular-oauth2-oidc';
import { JwksValidationHandler } from 'angular-oauth2-oidc-jwks';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private oauthService: OAuthService) { }

  configureAuth(){
    const authConfig: AuthConfig = {
      issuer: 'http://localhost:7080/realms/master',
      redirectUri: 'http://localhost:4200/USERINTERFACE/',
      clientId: "mypin-angular",
      scope: 'openid email profile',
      showDebugInformation: true,
      responseType: 'code'
    }
    this.oauthService.configure(authConfig);
    this.oauthService.tokenValidationHandler = new JwksValidationHandler();
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
    this.oauthService.setupAutomaticSilentRefresh();
    this.oauthService.oidc = true;
    this.oauthService.setStorage(sessionStorage);
  }
}
