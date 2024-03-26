import { Component } from '@angular/core';
import { OAuthLogger, OAuthService } from 'angular-oauth2-oidc';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  constructor(private oauthService: OAuthService, private oauthlogger:OAuthLogger) {
  }

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
  }
}
