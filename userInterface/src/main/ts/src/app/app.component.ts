import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { TuiDialogService } from '@taiga-ui/core';
import { jelloAnimation } from 'angular-animations';

import { NgxSpinnerService } from 'ngx-spinner';
import { AuthService } from './core/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  constructor(
    private auth: AuthService
  ) {
    auth.configureAuth();


  }

  ngOnInit(): void {
  }

}
