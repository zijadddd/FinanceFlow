import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { provideAnimations } from '@angular/platform-browser/animations';
import { TransactionResponse } from '../shared/models/transaction.model';
import { TransactionService } from '../shared/services/transaction.service';
import { AccountService } from '../shared/services/account.service';
import { AccountResponse } from '../shared/models/account.model';

@Component({
  selector: 'app-transactions',
  standalone: true,
  imports: [NgFor, NgIf],
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.css',
  encapsulation: ViewEncapsulation.ShadowDom,
  providers: [provideAnimations()],
})
export class TransactionsComponent implements OnInit {
  public transactions: TransactionResponse[];
  public accounts: AccountResponse[];
  public accountHolder: AccountResponse | null;
  public dropdownText: string = 'All accounts';
  public showMessage: boolean = false;

  constructor(
    private transactionService: TransactionService,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.transactionService
      .getAllTransactions()
      .subscribe((response: TransactionResponse[]) => {
        this.transactions = response;
      });

    this.accountService
      .getAllAccounts()
      .subscribe((response: AccountResponse[]) => {
        this.accounts = response;
      });

    if (Array.isArray(this.accounts) && this.accounts.length === 0) {
      this.showMessage = true;
    }
  }

  getAllTransactionsForAccount(id: number) {
    this.transactionService
      .getAllTransactionsForAccount(id)
      .subscribe((response: TransactionResponse[]) => {
        if (this.accountHolder) {
          this.accounts.push(this.accountHolder);
        }

        this.dropdownText = response[0].accountName;
        this.transactions = response;

        this.accountHolder =
          this.accounts.find((account) => account.id === id) ||
          ({} as AccountResponse);
        this.accounts = this.accounts.filter((account) => account.id !== id);
      });
  }

  getAllTransactions() {
    this.transactionService
      .getAllTransactions()
      .subscribe((response: TransactionResponse[]) => {
        if (this.accountHolder) {
          this.accounts.push(this.accountHolder);
        }

        this.transactions = response;
        this.dropdownText = 'All accounts';
        this.accountHolder = null;
      });
  }
}
