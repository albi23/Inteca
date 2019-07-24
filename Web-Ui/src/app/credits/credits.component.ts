import {Component, OnInit} from '@angular/core';
import {HttpRequestService} from '../http-request.service';
import {Credits} from '../credits.model';


@Component({
  selector: 'app-credits',
  templateUrl: './credits.component.html',
  styleUrls: ['./credits.component.css']
})
export class CreditsComponent {

  private credits: Credits[];
  private isRequiredTable: boolean;
  private selectedCredit = 'Consumer Loan';

  constructor(private httpRequest: HttpRequestService) {
  }


  onGetCreditsInformation() {
    this.httpRequest.requestForCredits('/Credits').subscribe((response: any) => {
      const credits: Credits[] = [];
      const data = (JSON.parse(JSON.stringify(response))) as Credits[];
      for (let i = 0; i < data.length; i++) {
        credits.push(new Credits(data[i].firstName, data[i].lastName, data[i].pesel,
          data[i].productName, data[i].productValue, data[i].creditName));
      }
      this.credits = credits;
      this.isRequiredTable = !this.isRequiredTable;
    }, err => this.httpRequest.errorHandler(err));
  }

  onSubmitNewCredit(name: string, surname: string, pesel: string, productName: string, productValue: string) {
    this.httpRequest.sendNewCredit(name, surname, pesel, productName, productValue, this.selectedCredit).subscribe(res => console.log(res));
  }

  selectChangeHandler(event: any) {
    this.selectedCredit = event.target.value;
  }
}
