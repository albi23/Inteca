import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { CreditsComponent } from './credits/credits.component';
import { HttpClientModule} from '@angular/common/http';
import { CreditsTableComponent } from './credits/credits-table/credits-table.component';

@NgModule({
  declarations: [
    AppComponent,
    CreditsComponent,
    CreditsTableComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
