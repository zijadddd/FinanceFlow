import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TransactionRequest, TransactionResponse } from '../models/transaction.model';
import { Observable } from 'rxjs';
import { TransactionApi } from '../api/transaction-api.constant';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private readonly httpClient: HttpClient) { }

  getAllTransactions(): Observable<TransactionResponse[]> {
    return this.httpClient.get<TransactionResponse[]>(TransactionApi.GET_TRANSACTIONS);
  }

  getAllTransactionsForAccount(id: number): Observable<TransactionResponse> {
    return this.httpClient.get<TransactionResponse>(TransactionApi.GET_TRANSACTIONS_FOR_ACCOUNT.replace('#', id.toString()));
  }

  commitTransaction(request: TransactionRequest): Observable<TransactionResponse> {
    return this.httpClient.post<TransactionResponse>(TransactionApi.COMMIT_TRANSACTION, request);
  }
}
