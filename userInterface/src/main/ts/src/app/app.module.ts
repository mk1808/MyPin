import { NgDompurifySanitizer } from "@tinkoff/ng-dompurify";
import { TuiRootModule, TuiDialogModule, TuiAlertModule, TUI_SANITIZER, TuiTextfieldControllerModule, TuiHintComponent, TuiHintModule, TuiButtonModule, TuiTooltipModule, TuiSvgModule } from "@taiga-ui/core";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from "@angular/forms";
import { TuiAccordionModule, TuiInputModule } from "@taiga-ui/kit";
import { UNIVERSAL_PROVIDERS } from "@ng-web-apis/universal";
import { NgxSpinnerModule } from "ngx-spinner";
import { NgIconsModule } from "@ng-icons/core";
import { ionFish } from '@ng-icons/ionicons';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
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
  bootstrap: [AppComponent]
})
export class AppModule { }
