import { KeyValuePipe, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { PopupComponent } from '../popup/popup.component';
import { CurrencyService } from '../shared/services/currency.service';
import {
  AccountRequest,
  AccountResponse,
} from '../shared/models/account.model';
import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { AccountService } from '../shared/services/account.service';
import { CommunicationService } from '../shared/services/communication.service';
import { WhichAction } from '../shared/models/which-action.model';

@Component({
  selector: 'app-account-modal',
  standalone: true,
  imports: [
    NgFor,
    NgIf,
    PopupComponent,
    KeyValuePipe,
    ReactiveFormsModule,
    FormsModule,
  ],
  templateUrl: './account-modal.component.html',
  styleUrl: './account-modal.component.css',
})
export class AccountModalComponent implements OnInit {
  public popupMessage: string = '';
  public popupType: boolean;
  public isPopupVisible: boolean;
  public createAccountBtnClicked: boolean = false;
  public currencies: { [key: string]: string }[] = [];

  public createAccountForm: FormGroup;

  constructor(
    private accountService: AccountService,
    private currencyService: CurrencyService,
    private formBuilder: FormBuilder,
    private communicationService: CommunicationService
  ) {}

  ngOnInit(): void {
    this.currencyService.getCurrencies().subscribe((response) => {
      this.currencies = response;
    });

    this.createAccountForm = this.formBuilder.group({
      currency: ['eur'],
      accountName: [''],
      balance: [''],
    });

    this.communicationService.action$.subscribe((action) => {
      if (action === WhichAction.CREATE_ACCOUNT) {
        this.createAccountModalOpen();
      }
    });
  }

  createAccountModalOpen() {
    this.createAccountBtnClicked = true;
  }

  cancelCreateAccountClicked() {
    this.createAccountBtnClicked = false;
  }

  onSubmit() {
    const request: AccountRequest = new AccountRequest();
    request.name = this.createAccountForm.value.accountName;
    request.balance = this.createAccountForm.value.balance;
    request.currencyCode = this.createAccountForm.value.currency;

    this.accountService.createAccount(request).subscribe(
      (response: AccountResponse) => {
        this.popupMessage = 'Account is successfully created.';
        this.popupType = true;
        this.isPopupVisible = true;
        this.communicationService.updateAccountsList();
        this.communicationService.updateFooter();

        this.createAccountForm.reset({
          currency: 'eur',
        });
        this.createAccountBtnClicked = false;

        setTimeout(() => {
          this.isPopupVisible = false;
        }, 5000);
      },
      (error: HttpErrorResponse) => {
        this.popupMessage = error.error.text;
        this.popupType = false;
        this.isPopupVisible = true;
        this.createAccountBtnClicked = false;

        setTimeout(() => {
          this.isPopupVisible = false;
        }, 5000);
        return throwError(() => error);
      }
    );
  }
}
