import { Injectable } from '@angular/core';
import { RestService } from '../rest.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationsApiService {

  apiURL = '/NOTIFICATIONS/api';

  constructor(private restService: RestService) { }

  get(): Observable<Notification[] | null> {
    return this.restService.get(`${this.apiURL}`);
  }

  confirm(id: string): Observable<null> {
    return this.restService.patch(`${this.apiURL}/${id}`, null);
  }

  create(notification: NotificationDto): Observable<Notification> {
    return this.restService.post(`${this.apiURL}`, notification);
  }

}
