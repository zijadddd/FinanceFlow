import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AccountRequest, AccountResponse } from '../models/account.model';
import { AccountApi } from '../api/account-api.constant';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private readonly httpClient: HttpClient) { }

  getAllAccounts(): Observable<AccountResponse[]> {
    return this.httpClient.get<AccountResponse[]>(AccountApi.GET_ACCOUNTS);
  }

  getAccount(id: number): Observable<AccountResponse> {
    return this.httpClient.get<AccountResponse>(AccountApi.GET_ACCOUNT.replace('#', id.toString()));
  }

  createAccount(request: AccountRequest): Observable<AccountResponse> {
    return this.httpClient.post<AccountResponse>(AccountApi.CREATE_ACCOUNT, request);
  }
}
