import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, retry, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  constructor(private httpClient: HttpClient) { }

  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(() => {
      return errorMessage;
    });
  }

  post<T, V>(path: string, data: T): Observable<V> {
    return this.postFullResponse<T, V>(path, data)
      .pipe(map(response => response.body!));
  }

  postFullResponse<T, V>(path: string, data: T): Observable<HttpResponse<V>> {
    return this.httpClient.post<V>(path, JSON.stringify(data), {
      observe: 'response'
    }).pipe(retry(1), catchError(this.handleError));
  }

  get<V>(path: string, params: any = {}): Observable<V | null> {
    return this.getFullResponse<V>(path, params)
      .pipe(map(response => response.body));
  }

  getFullResponse<V>(path: string, params: any = {}): Observable<HttpResponse<V>> {
    return this.httpClient.get<V>(path, {
      observe: 'response',
      params: new HttpParams(params)
    }).pipe(retry(1), catchError(this.handleError));
  }

  put<T, V>(path: string, data: T): Observable<V> {
    return this.putFullResponse<T, V>(path, data)
      .pipe(map(response => response.body!));
  }

  putFullResponse<T, V>(path: string, data: T): Observable<HttpResponse<V>> {
    return this.httpClient.put<V>(path, JSON.stringify(data), {
      observe: 'response'
    }).pipe(retry(1), catchError(this.handleError));
  }

  delete<V>(path: string): Observable<V> {
    return this.deleteFullResponse<V>(path)
      .pipe(map(response => response.body!));
  }

  deleteFullResponse<V>(path: string): Observable<HttpResponse<V>> {
    return this.httpClient.delete<V>(path, {
      observe: 'response'
    }).pipe(retry(1), catchError(this.handleError));
  }

  patch<T, V>(path: string, data: T): Observable<V> {
    return this.patchFullResponse<T, V>(path, data)
      .pipe(map(response => response.body!));
  }

  patchFullResponse<T, V>(path: string, data: T): Observable<HttpResponse<V>> {
    return this.httpClient.patch<V>(path, data, {
      observe: 'response'
    }).pipe(retry(1), catchError(this.handleError));
  }
}
