import { encapsulateStyle } from '@angular/compiler';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DefaultaccountService } from '../shared/services/defaultaccount.service';
import { DefaultAccountResponse } from '../shared/models/defaultaccount.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.css',
  encapsulation: ViewEncapsulation.ShadowDom
})
export class SettingsComponent implements OnInit {
  public exchangeRateChanged: string;
  public defaultAccount: DefaultAccountResponse;
  private currencyApi: string = "https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/#.json";
  public currencies: any;

  constructor(private defaultAccountService: DefaultaccountService, private htppClient: HttpClient) {}

  ngOnInit(): void {
    this.defaultAccountService.getDefaultAccount().subscribe((response: DefaultAccountResponse) => {
      this.defaultAccount = response;
    });

    this.htppClient.get(this.currencyApi.replace("#", this.defaultAccount.currencyCode.toLowerCase())).subscribe((response: any) => {
      this.exchangeRateChanged = response.date;
    });
  }

}
