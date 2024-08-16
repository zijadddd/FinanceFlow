import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DefaultaccountService } from '../shared/services/defaultaccount.service';
import {
  DefaultAccountRequest,
  DefaultAccountResponse,
} from '../shared/models/defaultaccount.model';
import { HttpClient, provideHttpClient } from '@angular/common/http';
import { Currency } from '../shared/models/currency.model';
import { CurrencyService } from '../shared/services/currency.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { KeyValuePipe } from '@angular/common';
import { CurrencyApi } from '../shared/api/currency-api.constant';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [NgFor, NgIf, KeyValuePipe],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.css',
  encapsulation: ViewEncapsulation.ShadowDom,
  providers: [KeyValuePipe],
})
export class SettingsComponent implements OnInit {
  public exchangeRateChanged: string;
  public defaultAccount: DefaultAccountResponse = new DefaultAccountResponse();
  public currencies: Currency[];
  public deleteAllDataBtnClicked: boolean = false;

  constructor(
    private defaultAccountService: DefaultaccountService,
    private httpClient: HttpClient,
    private currencyService: CurrencyService
  ) {}

  ngOnInit(): void {
    this.defaultAccountService.getDefaultAccount().subscribe((response) => {
      this.defaultAccount = response;
    });

    this.currencyService.getCurrencies().subscribe((response) => {
      this.currencies = response;
    });

    this.httpClient
      .get(
        CurrencyApi.GET_CURRENCY_COURSE.replace(
          '#',
          this.defaultAccount.currencyCode.toLowerCase()
        )
      )
      .subscribe((response: any) => {
        this.exchangeRateChanged = response.date;
      });
  }

  changeDefaultCurrency(currencyCode: string) {
    const currency: Currency = new Currency();
    currency.currencyCode = currencyCode.toUpperCase();
    this.defaultAccountService
      .changeCurrencyCode(currency)
      .subscribe((response) => {
        console.log(response);
      });
  }

  deleteAllData() {
    this.deleteAllDataBtnClicked = true;
  }

  confirmDeletingData() {
    this.defaultAccountService.deleteAllData().subscribe((response) => {
      console.log(response);
    });
    this.deleteAllDataBtnClicked = false;
  }

  cancelDeleteAllDataClicked() {
    this.deleteAllDataBtnClicked = false;
  }
}
