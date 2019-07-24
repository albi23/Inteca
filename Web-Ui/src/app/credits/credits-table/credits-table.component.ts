import {Component, Input, OnInit} from '@angular/core';
import {Credits} from '../../credits.model';

@Component({
  selector: 'app-credits-table',
  templateUrl: './credits-table.component.html',
  styleUrls: ['./credits-table.component.css']
})
export class CreditsTableComponent implements OnInit {

  @Input() credits: Credits[];
  constructor() { }

  ngOnInit() {
  }

}
