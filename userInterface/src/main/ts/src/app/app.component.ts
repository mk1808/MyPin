import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { TuiDialogService } from '@taiga-ui/core';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit{
  title = 'my-pin';
  readonly testForm = new FormGroup({
    testValue: new FormControl('mail@mail.ru'),
  });

  constructor(
    @Inject(TuiDialogService)
    private readonly dialogs: TuiDialogService,
    private spinner: NgxSpinnerService
  ) { }

  ngOnInit(): void {
     this.spinner.show();

    setTimeout(() => {
      this.spinner.hide();
    }, 5000);
  }

  open() {
    this.dialogs.open('Hello!').subscribe();
  }
}
