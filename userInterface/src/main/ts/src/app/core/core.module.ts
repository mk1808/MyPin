import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PermissionsService } from './guards/map-details.guard';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers:[
    PermissionsService
  ]
})
export class CoreModule { }
