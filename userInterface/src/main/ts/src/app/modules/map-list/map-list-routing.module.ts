import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MapListComponent } from './components/map-list.component';

const routes: Routes = [{ path: '', component: MapListComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MapListRoutingModule { }
