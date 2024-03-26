import { Injectable, inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot } from '@angular/router';
import { OAuthService } from 'angular-oauth2-oidc';

@Injectable()
export class PermissionsService {
  constructor(private oauthService: OAuthService) { }
  
  canActivate(): boolean {
    const hasIdToken = this.oauthService.hasValidIdToken(),
      hasAccessToken = this.oauthService.hasValidAccessToken(),
      hasRights = hasIdToken && hasAccessToken;
    if (!hasRights) {
      console.log("cant enter maps details")
    }

    return (hasRights);
  }
}

export const mapDetailsGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  return inject(PermissionsService).canActivate();
};