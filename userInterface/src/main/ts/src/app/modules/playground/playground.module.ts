import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PlaygroundRoutingModule } from './playground-routing.module';
import { PlaygroundComponent } from './components/playground.component';
import { TUI_SANITIZER, TuiRootModule, TuiDialogModule, TuiAlertModule, TuiTextfieldControllerModule, TuiHintModule, TuiButtonModule, TuiTooltipModule, TuiSvgModule } from "@taiga-ui/core";

import { ReactiveFormsModule } from "@angular/forms";
import { TuiAccordionModule, TuiInputModule } from "@taiga-ui/kit";

import { NgxSpinnerModule } from "ngx-spinner";
import { NgxSpinnerService } from 'ngx-spinner';
import { NgIconsModule } from "@ng-icons/core";
import { ionFish } from '@ng-icons/ionicons';
import { UNIVERSAL_PROVIDERS } from "@ng-web-apis/universal";
import { provideClientHydration } from '@angular/platform-browser';
import { NgDompurifySanitizer } from "@tinkoff/ng-dompurify";
import { AuthRoutingComponent } from './components/auth-routing/auth-routing.component';


@NgModule({
  declarations: [
    PlaygroundComponent,
    AuthRoutingComponent
  ],
  imports: [
    CommonModule,
    PlaygroundRoutingModule,
    TuiRootModule,
    TuiDialogModule,
    TuiAlertModule,
    ReactiveFormsModule,
    TuiInputModule,
    TuiTextfieldControllerModule,
    TuiHintModule,
    TuiButtonModule,
    TuiAccordionModule,
    TuiTooltipModule,
    NgxSpinnerModule,
    TuiSvgModule,
    NgIconsModule.withIcons({ ionFish }),
  ],
  providers: [
    UNIVERSAL_PROVIDERS,
    provideClientHydration(),
    { provide: TUI_SANITIZER, useClass: NgDompurifySanitizer },
  ],
  exports:[
    AuthRoutingComponent
  ]
})
export class PlaygroundModule { }
