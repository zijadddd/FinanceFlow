import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DefaultAccountApi } from '../api/default-account-api.constant';
import { DefaultAccountRequest, DefaultAccountResponse } from '../models/defaultaccount.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DefaultaccountService {

  constructor(private readonly httpClient: HttpClient) { }

  getDefaultAccount(): Observable<DefaultAccountResponse> {
    return this.httpClient.get<DefaultAccountResponse>(DefaultAccountApi.GET_DEFAULT_ACCOUNT);
  }

  changeCurrencyCode(defaultAccountRequest: DefaultAccountRequest): Observable<DefaultAccountResponse> {
    return this.httpClient.post<DefaultAccountResponse>(DefaultAccountApi.CHANGE_CURRENCY_CODE, defaultAccountRequest);
  }
}
