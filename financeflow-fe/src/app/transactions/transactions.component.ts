import { NgFor, NgIf } from '@angular/common';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { provideAnimations } from '@angular/platform-browser/animations';
import { TransactionResponse } from '../shared/models/transaction.model';
import { TransactionService } from '../shared/services/transaction.service';
import { AccountService } from '../shared/services/account.service';
import { AccountResponse } from '../shared/models/account.model';
import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { PopupComponent } from '../popup/popup.component';
import { Title } from '@angular/platform-browser';
import { CommunicationService } from '../shared/services/communication.service';
import { WhichAction } from '../shared/models/which-action.model';
import { LoaderComponent } from '../loader/loader.component';

@Component({
  selector: 'app-transactions',
  standalone: true,
  imports: [NgFor, NgIf, PopupComponent, LoaderComponent],
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.css',
  encapsulation: ViewEncapsulation.ShadowDom,
  providers: [provideAnimations()],
})
export class TransactionsComponent implements OnInit {
  public transactions: TransactionResponse[] = [];
  public accounts: AccountResponse[] = [];
  public accountHolder: AccountResponse | null;
  public dropdownText: string = 'All accounts';
  public popupMessage: string = '';
  public popupType: boolean;
  public isPopupVisible: boolean;
  public isLoaderVisible: boolean = false;

  constructor(
    private transactionService: TransactionService,
    private accountService: AccountService,
    private titleService: Title,
    private communicationService: CommunicationService
  ) {}

  ngOnInit(): void {
    this.isLoaderVisible = true;
    this.titleService.setTitle('FinanceFlow - Transactions');

    this.accountService
      .getAllAccounts()
      .subscribe((response: AccountResponse[]) => {
        this.accounts = response;
      });

    this.transactionService.getAllTransactions().subscribe(
      (response: TransactionResponse[]) => {
        this.transactions = response;
        this.isLoaderVisible = false;
      },
      (error: HttpErrorResponse) => {
        this.isLoaderVisible = false;
      }
    );

    this.communicationService.action$.subscribe((action) => {
      if (action === WhichAction.UPDATE_TRANSACTIONS) {
        this.updateTransactionsList();
      }
    });
  }

  getAllTransactionsForAccount(id: number) {
    this.transactionService.getAllTransactionsForAccount(id).subscribe(
      (response: TransactionResponse[]) => {
        if (this.accountHolder) {
          this.accounts.push(this.accountHolder);
        }

        this.dropdownText = response[0].accountName;
        this.transactions = response;

        this.accountHolder =
          this.accounts.find((account) => account.id === id) ||
          ({} as AccountResponse);
        this.accounts = this.accounts.filter((account) => account.id !== id);
      },
      (error: HttpErrorResponse) => {
        this.popupMessage = error.error.text;
        this.popupType = false;
        this.isPopupVisible = true;

        setTimeout(() => {
          this.isPopupVisible = false;
        }, 5000);
        return throwError(() => error);
      }
    );
  }

  getAllTransactions() {
    this.transactionService.getAllTransactions().subscribe(
      (response: TransactionResponse[]) => {
        if (this.accountHolder) {
          this.accounts.push(this.accountHolder);
        }

        this.transactions = response;
        this.dropdownText = 'All accounts';
        this.accountHolder = null;
      },
      (error: HttpErrorResponse) => {
        this.popupMessage = error.error.text;
        this.popupType = false;
        this.isPopupVisible = true;

        setTimeout(() => {
          this.isPopupVisible = false;
        }, 5000);
        return throwError(() => error);
      }
    );
  }

  updateTransactionsList() {
    this.transactionService.getAllTransactions().subscribe((response) => {
      this.transactions = response;
    });
  }
}
