import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { TuiDialogService } from '@taiga-ui/core';
import { jelloAnimation } from 'angular-animations';
import { AuthConfig, OAuthLogger, OAuthService } from 'angular-oauth2-oidc';
import { JwksValidationHandler } from 'angular-oauth2-oidc-jwks';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  animations: [jelloAnimation()]
})
export class AppComponent implements OnInit {
  title = 'my-pin';
  animationState = false;
  readonly testForm = new FormGroup({
    testValue: new FormControl('mail@mail.ru'),
  });

  constructor(
    @Inject(TuiDialogService)
    private readonly dialogs: TuiDialogService,
    private spinner: NgxSpinnerService,
    private oauthService: OAuthService,
    private oauthLogger:OAuthLogger,
    private http:HttpClient
  ) {
    console.log(window.location.origin + '/maps')
    const authConfig: AuthConfig = {  
      issuer: 'http://localhost:7080/realms/master',
      //'http://localhost:7080',
      //redirectUri: window.location.href,
      redirectUri:'http://localhost:4200/USERINTERFACE/',
      clientId: "mypin-angular",
      scope: 'openid email profile',//'openid profile email mypin-angular-dedicated', // offline_access api name
      showDebugInformation: true,
      responseType: 'code'
  }
  this.oauthService.configure(authConfig);
  this.oauthService.tokenValidationHandler = new JwksValidationHandler();
  //this.oauthService.tokenEndpoint = 
  this.oauthService.loadDiscoveryDocumentAndTryLogin();
  this.oauthService.setupAutomaticSilentRefresh();
    // URL of the SPA to redirect the user to after login
  ////  this.oauthService.redirectUri = window.location.origin + '/index.html';

    // The SPA's id. The SPA is registerd with this id at the auth-server
  ////  this.oauthService.clientId = "mypin-angular";

    // set the scope for the permissions the client should request
    // The first three are defined by OIDC. The 4th is a usecase-specific one
   //// this.oauthService.scope = "openid profile email mypin-angular-dedicated";

    // set to true, to receive also an id_token via OpenId Connect (OIDC) in addition to the
    // OAuth2-based access_token
    this.oauthService.oidc = true; // ID_Token

   //// this.oauthService.showDebugInformation = true;

    // Use setStorage to use sessionStorage or another implementation of the TS-type Storage
    // instead of localStorage
    this.oauthService.setStorage(sessionStorage);

    // Discovery Document of your AuthServer as defined by OIDC
  ////  let url = 'http://localhost:7080/realms/master/.well-known/openid-configuration';

    // Load Discovery Document and then try to login the user
  ////  this.oauthService.loadDiscoveryDocument(url).then(() => {

      // This method just tries to parse the token(s) within the url when
      // the auth-server redirects the user back to the web-app
      // It dosn't send the user the the login page
      /*this.oauthService.tryLogin({
        validationHandler: context => {
            var search = new URLSearchParams();
            search.set('token', context.idToken); 
            search.set('client_id', this.oauthService.clientId!);
            const validationUrl = ;
            return http.get(validationUrl, { search }).toPromise();
        }
    });*/

 ////   });

  }

  ngOnInit(): void {
    /*  this.spinner.show();
 
     setTimeout(() => {
       this.spinner.hide();
     }, 1000);*/
    this.animate()
  }

  open() {
    this.dialogs.open('Hello!').subscribe();
  }

  animate() {
    this.animationState = false;
    setTimeout(() => {
      this.animationState = true;

    }, 5000);
  }

}
