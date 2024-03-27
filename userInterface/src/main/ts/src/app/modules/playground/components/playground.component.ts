import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { TuiDialogService } from '@taiga-ui/core';
import { jelloAnimation } from 'angular-animations';

import { NgxSpinnerService } from 'ngx-spinner';
import { MapsApiService } from '../../../core/services/api/maps-api.service';

@Component({
  selector: 'app-playground',
  templateUrl: './playground.component.html',
  styleUrl: './playground.component.scss',
  animations: [jelloAnimation()]
})
export class PlaygroundComponent implements OnInit{
  title = 'my-pin';
  animationState = false;
  readonly testForm = new FormGroup({
    testValue: new FormControl('mail@mail.ru'),
  });

  constructor(
    @Inject(TuiDialogService)
    private readonly dialogs: TuiDialogService,
    private spinner: NgxSpinnerService,
    private mapsService:MapsApiService
  ) {

  }

  ngOnInit(): void {
    /*  this.spinner.show();
 
     setTimeout(() => {
       this.spinner.hide();
     }, 1000);*/
    this.animate();
    
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

  send(){
    console.log("sending...")
    this.mapsService.get('2b17e16e-0153-4d7c-9881-d07bbdb23ada').subscribe(x=>{
      console.log(x);
    })
  }
}
