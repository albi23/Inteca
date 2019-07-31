import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Credits} from './credits.model';
import {catchError, map} from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class HttpRequestService {

  private URL = 'http://credit:8080';
  private credits: Credits[];

  constructor(private http: HttpClient) {
  }


  public requestForCredits(url: string) {

    const options: any = {json: true};
    return this.http.request('GET', this.URL + url, options);
  }


  public sendNewCredit(name: string, surname: string, pesel: string, productName: string, productValue: string, selectedCredit: string) {
    return this.http.request('GET', this.URL + '/Credit?nameOfCredit=' + selectedCredit + '&clientName=' + name + '&clientSurname=' + surname + '&clientPesel=' + pesel + '&productName=' + productName + '&productValue=' + productValue);
  }
  errorHandler(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError(
      'Something bad happened; please try again later.');
  }


  getBackendUrl(): string {
    return this.URL;
  }

  setBackendUrl(URL: string) {
    this.URL = URL;
  }

}
