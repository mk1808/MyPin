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

  
}
