import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PermissionsService } from './guards/map-details.guard';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { NgIconsModule } from '@ng-icons/core';
import { ionLogoGithub, ionNotificationsOutline, ionNotifications } from '@ng-icons/ionicons';
import { RouterModule } from '@angular/router';
import { TuiNavigationModule } from '@taiga-ui/experimental';
import { TuiButtonModule } from '@taiga-ui/core';
import { TuiBadgedContentModule } from '@taiga-ui/kit';


@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    NgIconsModule.withIcons({ ionLogoGithub, ionNotificationsOutline, ionNotifications }),
    RouterModule,
    TuiNavigationModule,
    TuiButtonModule,
    TuiBadgedContentModule,
  ],
  providers: [
    PermissionsService
  ],
  exports: [
    HeaderComponent,
    FooterComponent
  ]
})
export class CoreModule { }
