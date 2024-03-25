import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { TuiDialogService } from '@taiga-ui/core';
import { jelloAnimation } from 'angular-animations';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  animations: [jelloAnimation()]
})
export class AppComponent implements OnInit{
  title = 'my-pin';
  animationState = false;
  readonly testForm = new FormGroup({
    testValue: new FormControl('mail@mail.ru'),
  });

  constructor(
    @Inject(TuiDialogService)
    private readonly dialogs: TuiDialogService,
    private spinner: NgxSpinnerService
  ) { }

  ngOnInit(): void {
   /*  this.spinner.show();

    setTimeout(() => {
      this.spinner.hide();
    }, 1000);*/
    this.animate()
  }

  open() {
    this.dialogs.open('Hello!').subscribe();
  }

  animate() {
    this.animationState = false;
    setTimeout(() => {
      this.animationState = true;
     
    }, 5000);
  }
}
