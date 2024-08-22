import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { AccountResponse } from '../shared/models/account.model';
import { ExpenseType } from '../shared/models/expense.model';
import { AccountService } from '../shared/services/account.service';
import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import {
  TransactionRequest,
  TransactionResponse,
} from '../shared/models/transaction.model';
import { TransactionService } from '../shared/services/transaction.service';
import { CommunicationService } from '../shared/services/communication.service';
import { PopupComponent } from '../popup/popup.component';
import { WhichAction } from '../shared/models/which-action.model';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-transaction-modal',
  standalone: true,
  imports: [PopupComponent, FormsModule, ReactiveFormsModule, NgIf, NgFor],
  templateUrl: './transaction-modal.component.html',
  styleUrl: './transaction-modal.component.css',
})
export class TransactionModalComponent implements OnInit {
  public expenseTypes = Object.values(ExpenseType);
  public accounts: AccountResponse[] = [];
  public commitTransactionBtnClicked: boolean = false;
  public commitTransactionForm: FormGroup;

  public popupMessage: string = '';
  public popupType: boolean;
  public isPopupVisible: boolean;

  constructor(
    private accountService: AccountService,
    private formBuilder: FormBuilder,
    private transactionService: TransactionService,
    private communicationService: CommunicationService
  ) {}

  ngOnInit(): void {
    this.accountService.getAllAccounts().subscribe((response) => {
      this.accounts = response;
    });

    this.commitTransactionForm = this.formBuilder.group({
      description: [''],
      type: [ExpenseType.TRANSFER],
      account: [1],
      amount: [''],
    });

    this.communicationService.action$.subscribe((action) => {
      if (action === WhichAction.COMMIT_TRANSACTION) {
        this.commitTransactionModalOpen();
      }
    });
  }

  commitTransactionModalOpen() {
    this.accountService.getAllAccounts().subscribe((response) => {
      this.accounts = response;
    });
    this.commitTransactionBtnClicked = true;
  }

  closeCommitTransactionModalClicked() {
    this.commitTransactionBtnClicked = false;
  }

  onSubmit() {
    const request: TransactionRequest = new TransactionRequest();
    request.accountId = this.commitTransactionForm.value.account;
    request.amount = this.commitTransactionForm.value.amount;
    request.description = this.commitTransactionForm.value.description;
    request.expense = this.commitTransactionForm.value.type;

    this.transactionService.commitTransaction(request).subscribe(
      (response: TransactionResponse) => {
        this.popupMessage = 'Transaction is successfully comitted.';
        this.popupType = true;
        this.isPopupVisible = true;

        this.commitTransactionForm.reset({
          type: ExpenseType.TRANSFER,
          account: 1,
        });
        this.commitTransactionBtnClicked = false;

        this.communicationService.updateAccountsList();
        this.communicationService.updateTransactionsList();
        this.communicationService.updateFooter();
        setTimeout(() => {
          this.isPopupVisible = false;
        }, 5000);
      },
      (error: HttpErrorResponse) => {
        this.popupMessage = error.error.text;
        this.popupType = false;
        this.isPopupVisible = true;

        this.commitTransactionBtnClicked = false;

        setTimeout(() => {
          this.isPopupVisible = false;
        }, 5000);
        return throwError(() => error);
      }
    );
  }
}
