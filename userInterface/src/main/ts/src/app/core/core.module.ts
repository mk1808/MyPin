import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PermissionsService } from './guards/map-details.guard';
import { AuthInterceptorService, authInterceptor } from './interceptors/auth.interceptor';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptors } from '@angular/common/http';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers:[
    PermissionsService,
    AuthInterceptorService,
    provideHttpClient(
      withInterceptors([authInterceptor])
    ),
  ]
})
export class CoreModule { }
