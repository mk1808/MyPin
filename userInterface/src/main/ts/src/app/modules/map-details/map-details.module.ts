import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MapDetailsRoutingModule } from './map-details-routing.module';
import { MapDetailsComponent } from './components/map-details.component';


@NgModule({
  declarations: [
    MapDetailsComponent
  ],
  imports: [
    CommonModule,
    MapDetailsRoutingModule
  ]
})
export class MapDetailsModule { }
