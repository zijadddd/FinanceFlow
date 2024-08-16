import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DefaultAccountApi } from '../api/default-account-api.constant';
import {
  DefaultAccountRequest,
  DefaultAccountResponse,
} from '../models/defaultaccount.model';
import { catchError, Observable, throwError } from 'rxjs';
import { CurrencyApi } from '../api/currency-api.constant';
import { Currency } from '../models/currency.model';

@Injectable({
  providedIn: 'root',
})
export class DefaultaccountService {
  constructor(private readonly httpClient: HttpClient) {}

  getDefaultAccount(): Observable<DefaultAccountResponse> {
    return this.httpClient.get<DefaultAccountResponse>(
      DefaultAccountApi.GET_DEFAULT_ACCOUNT
    );
  }

  changeCurrencyCode(request: Currency): Observable<DefaultAccountResponse> {
    return this.httpClient.post<DefaultAccountResponse>(
      DefaultAccountApi.CHANGE_CURRENCY_CODE,
      request
    );
  }

  deleteAllData(): Observable<string> {
    return this.httpClient.delete<string>(DefaultAccountApi.DELETE_ALL_DATA);
  }
}
