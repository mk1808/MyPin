import { Component } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { NotificationDto } from '../../models/NotificationDto';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  notifications: NotificationDto[] = [{} as NotificationDto];

  constructor(private oauthService: OAuthService) {
  }

  public get isLogged() {
    const hasIdToken = this.oauthService.hasValidIdToken(),
      hasAccessToken = this.oauthService.hasValidAccessToken(),
      hasRights = hasIdToken && hasAccessToken;
    return hasRights
  }

  public get existsNotifications() {
    return this.notifications.length > 0;
  }

  public login() {
    this.oauthService.initImplicitFlow();
  }

  public logout() {
    this.oauthService.logOut();
  }
}
