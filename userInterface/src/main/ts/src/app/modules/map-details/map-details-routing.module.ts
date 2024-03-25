import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MapDetailsComponent } from './components/map-details.component';

const routes: Routes = [{ path: '', component: MapDetailsComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MapDetailsRoutingModule { }
