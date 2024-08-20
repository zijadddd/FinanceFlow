import {
  AfterViewInit,
  Component,
  OnInit,
  ViewEncapsulation,
} from '@angular/core';
import { DefaultaccountService } from '../shared/services/defaultaccount.service';
import { DefaultAccountResponse } from '../shared/models/defaultaccount.model';
import { HttpClient } from '@angular/common/http';
import { Currency, CurrencyRequest } from '../shared/models/currency.model';
import { CurrencyService } from '../shared/services/currency.service';
import { NgFor, NgIf } from '@angular/common';
import { KeyValuePipe } from '@angular/common';
import { CurrencyApi } from '../shared/api/currency-api.constant';
import { PopupComponent } from '../popup/popup.component';
import { CommunicationService } from '../shared/services/communication.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [NgFor, NgIf, KeyValuePipe, PopupComponent],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.css',
  encapsulation: ViewEncapsulation.ShadowDom,
  providers: [KeyValuePipe],
})
export class SettingsComponent implements AfterViewInit {
  public exchangeRateChanged: string;
  public defaultAccount: DefaultAccountResponse = new DefaultAccountResponse();
  public currencies: Currency[] = [];
  public deleteAllDataBtnClicked: boolean = false;
  public popupMessage: string = '';
  public popupType: boolean;
  public isPopupVisible: boolean;

  constructor(
    private defaultAccountService: DefaultaccountService,
    private httpClient: HttpClient,
    private currencyService: CurrencyService,
    private communicationService: CommunicationService,
    private titleService: Title
  ) {}

  ngAfterViewInit(): void {
    this.titleService.setTitle('FinanceFlow - Settings');

    this.defaultAccountService.getDefaultAccount().subscribe((response) => {
      this.defaultAccount = response;

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
    });

    this.currencyService.getCurrencies().subscribe((response) => {
      this.currencies = response;
    });
  }

  changeDefaultCurrency(currencyCode: string): void {
    const currency: CurrencyRequest = new CurrencyRequest();
    currency.currencyCode = currencyCode.toUpperCase();
    this.defaultAccountService
      .changeCurrencyCode(currency)
      .subscribe((response) => {
        this.popupMessage = response.text;
        this.popupType = true;
        this.isPopupVisible = true;

        this.getDefaultAccount();
        this.notifySibling();

        setTimeout(() => {
          this.isPopupVisible = false;
        }, 5000);
      });
  }

  deleteAllData(): void {
    this.deleteAllDataBtnClicked = true;
  }

  confirmDeletingData(): void {
    this.defaultAccountService.deleteAllData().subscribe((response) => {
      this.popupMessage = response.text;
      this.popupType = true;
      this.isPopupVisible = true;

      setTimeout(() => {
        this.isPopupVisible = false;
      }, 5000);
    });
    this.deleteAllDataBtnClicked = false;
  }

  cancelDeleteAllDataClicked(): void {
    this.deleteAllDataBtnClicked = false;
  }

  getDefaultAccount(): void {
    this.defaultAccountService.getDefaultAccount().subscribe((response) => {
      this.defaultAccount = response;
    });
  }

  notifySibling() {
    this.communicationService.updateFooter();
  }
}
