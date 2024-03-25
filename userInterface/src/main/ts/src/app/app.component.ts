import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { TuiDialogService } from '@taiga-ui/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'my-pin';
  readonly testForm = new FormGroup({
    testValue: new FormControl('mail@mail.ru'),
  });

  constructor(
    @Inject(TuiDialogService)
    private readonly dialogs: TuiDialogService,
  ) { }

  open() {
    this.dialogs.open('Hello!').subscribe();
  }
}
