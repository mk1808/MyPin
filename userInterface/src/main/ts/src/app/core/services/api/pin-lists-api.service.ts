import { Injectable } from '@angular/core';
import { RestService } from '../rest.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PinListsApiService {

  apiURL = '/PINLISTS/api';

  constructor(private restService: RestService) { }

  get(mapId: string): Observable<PinListDto[] | null> {
    return this.restService.get(`${this.apiURL}/${mapId}`);
  }

  createPinList(pinList: PinList): Observable<PinList> {
    return this.restService.post(`${this.apiURL}`, pinList);
  }

  update(pinList: PinList): Observable<PinList> {
    return this.restService.put(`${this.apiURL}`, pinList);
  }

  delete(pinListId: string): Observable<PinList> {
    return this.restService.delete(`${this.apiURL}/${pinListId}`);
  }

  createPin(pin: Pin): Observable<Pin> {
    return this.restService.post(`${this.apiURL}/pin`, pin);
  }

  updatePin(pin: Pin): Observable<Pin> {
    return this.restService.put(`${this.apiURL}/pin`, pin);
  }

  deletePin(pinId: string): Observable<Pin> {
    return this.restService.delete(`${this.apiURL}/pin/${pinId}`);
  }

}
