import { Injectable } from '@angular/core';
import { RestService } from '../rest.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersApiService {

  apiURL = '/USERS/api';

  constructor(private restService: RestService) { }

  register(registerDto: RegisterDto): Observable<AppUser | null> {
    return this.restService.post(`${this.apiURL}`, registerDto);
  }

  get(id: string): Observable<AppUser | null> {
    return this.restService.get(`${this.apiURL}/${id}`);
  }

  getByEmail(email: string): Observable<AppUser | null> {
    return this.restService.get(`${this.apiURL}`, { email });
  }

}
