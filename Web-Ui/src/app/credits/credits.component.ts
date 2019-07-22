import {Component, OnInit} from '@angular/core';
import {HttpRequestService} from '../http-request.service';


@Component({
  selector: 'app-credits',
  templateUrl: './credits.component.html',
  styleUrls: ['./credits.component.css']
})
export class CreditsComponent  {


  public data: any;

  constructor(private httpRequest: HttpRequestService) {
  }


  onGetCreditsInformation() {
    this.httpRequest
      .getCredit('/Credits');
      // .subscribe(
      //   data => {
      //     console.log(data);
      //     this.data = data;
      //   },
      //   err => {
      //     console.log(err);
      //   }
      // );
  }
}
