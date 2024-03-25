import { Injectable } from '@angular/core';
import { RestService } from '../rest.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SynchronizationApiService {

  apiURL = '/SYNCHRONIZATION/api';

  constructor(private restService: RestService) { }

  sendSynchronizationMessage(synchronizationDto: SynchronizationDto): Observable<SynchronizationDto | null> {
    return this.restService.post(`${this.apiURL}`, synchronizationDto);
  }

}
