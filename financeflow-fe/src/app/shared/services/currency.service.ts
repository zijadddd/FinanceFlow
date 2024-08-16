import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Currency } from '../models/currency.model';
import { CurrencyApi } from '../api/currency-api.constant';

@Injectable({
  providedIn: 'root',
})
export class CurrencyService {
  constructor(private httpClient: HttpClient) {}

  getCurrencies(): Observable<Currency[]> {
    return this.httpClient.get<Currency[]>(CurrencyApi.GET_ALL_CURRENCIES);
  }
}
