import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { mapDetailsGuard } from './core/guards/map-details.guard';

const routes: Routes = [

  { path: 'home', loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule) },
  { path: 'playground', loadChildren: () => import('./modules/playground/playground.module').then(m => m.PlaygroundModule) },
  { path: 'maps', loadChildren: () => import('./modules/map-list/map-list.module').then(m => m.MapListModule) },
  { path: 'maps/:id', loadChildren: () => import('./modules/map-details/map-details.module').then(m => m.MapDetailsModule), canActivate: [mapDetailsGuard],}, 
  { path: '',   redirectTo: '/home', pathMatch: 'full' },
//  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
