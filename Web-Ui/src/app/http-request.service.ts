import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpRequestService {

  private URL = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }


  public getCredit(url: string) {

    const header = new HttpHeaders({
      'Access-Control-Allow-Origin': '*'
    });
    const options = {headers: header};

    this.http.request('GET', this.URL + url , options).subscribe(
      res => console.log(res), err => console.log(err));
  }



  getBackendUrl(): string {
    return this.URL;
  }

  setBackendUrl(URL: string) {
    this.URL = URL;
  }

}
