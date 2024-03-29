import { Component } from '@angular/core';
import { OAuthLogger, OAuthService } from 'angular-oauth2-oidc';

@Component({
  selector: 'app-auth-routing',
  templateUrl: './auth-routing.component.html',
  styleUrl: './auth-routing.component.scss'
})
export class AuthRoutingComponent {
  constructor(private oauthService: OAuthService, private oauthlogger:OAuthLogger) {
  }

  accessToken="";

  public login() {
      this.oauthService.initImplicitFlow();
  }

  public logout() {
      this.oauthService.logOut();
  }

  public get userName() {

      var claims = this.oauthService.getIdentityClaims();
      if (!claims) return null;

      return claims['preferred_username'];
  }

  public test(){
    let a=0;
    this.accessToken = this.oauthService.getAccessToken();
  }
}
