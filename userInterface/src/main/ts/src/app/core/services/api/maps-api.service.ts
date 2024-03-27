import { Injectable } from '@angular/core';
import { RestService } from '../rest.service';
import { Observable } from 'rxjs';
import { MpMap } from '../../models/MpMap';
import { MapSort } from '../../models/MapSort';
import { Sharing } from '../../models/Sharing';
import { SharingDto } from '../../models/SharingDto';

@Injectable({
  providedIn: 'root'
})
export class MapsApiService {
  apiURL = '/MAPS/api';

  constructor(private restService: RestService) { }

  create(map: MpMap): Observable<MpMap> {
    return this.restService.post(`${this.apiURL}`, map); 
  }

  get(id: string): Observable<MpMap | null> {
    return this.restService.get(`${this.apiURL}/${id}`);
  }

  search(params: { title?: string, sort?: MapSort, isSharedWithMe?: boolean, isMyOwn?: boolean } = {}): Observable<MpMap[] | null> {
    return this.restService.get(`${this.apiURL}/search`, params);
  }

  patchTitle(id: string, title: string): Observable<null> {
    return this.restService.patch(`${this.apiURL}/${id}/title?title=${title}`, null);
  }

  patchUpdated(id: string): Observable<null> {
    return this.restService.patch(`${this.apiURL}/${id}/updated`, null);
  }

  share(id: string, sharingDto: SharingDto): Observable<Sharing> {
    return this.restService.post(`${this.apiURL}/${id}/share`, sharingDto);
  }

  notify(id: string): Observable<null> {
    return this.restService.post(`${this.apiURL}/${id}/notify`, null);
  }

}
