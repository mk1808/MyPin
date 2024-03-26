import { Injectable, inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot } from '@angular/router';
import { OAuthService } from 'angular-oauth2-oidc';


@Injectable({
  providedIn: 'root'
})
class PermissionsService {
  constructor(private oauthService: OAuthService){}
  canActivate(): boolean {
    var hasIdToken = this.oauthService.hasValidIdToken();
    var hasAccessToken = this.oauthService.hasValidAccessToken();
    var hasRights = hasIdToken && hasAccessToken;
    if (!hasRights){
      console.log("cant enter maps details")
    }

    return (hasRights);
  }

}

export const mapDetailsGuard: CanActivateFn = (route: ActivatedRouteSnapshot, 
  state: RouterStateSnapshot) => {
    return inject(PermissionsService).canActivate();
};
