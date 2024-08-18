import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {
  AccountRequest,
  AccountResponse,
} from '../shared/models/account.model';
import { AccountService } from '../shared/services/account.service';
import { provideAnimations } from '@angular/platform-browser/animations';
import { NgFor, NgIf } from '@angular/common';
import { PopupComponent } from '../popup/popup.component';
import { catchError, throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { CurrencyService } from '../shared/services/currency.service';
import { KeyValuePipe } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { DefaultaccountService } from '../shared/services/defaultaccount.service';

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [
    NgFor,
    NgIf,
    PopupComponent,
    KeyValuePipe,
    ReactiveFormsModule,
    FormsModule,
  ],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css',
  encapsulation: ViewEncapsulation.ShadowDom,
  providers: [provideAnimations()],
})
export class AccountsComponent implements OnInit {
  public accounts: AccountResponse[] = [];
  public popupMessage: string = '';
  public popupType: boolean;
  public isPopupVisible: boolean;
  public createAccountBtnClicked: boolean = false;
  public currencies: { [key: string]: string }[] = [];

  public createAccountForm: FormGroup;
  public defaultSelectedCurrency = 'eur';

  constructor(
    private accountService: AccountService,
    private currencyService: CurrencyService,
    private formBuilder: FormBuilder,
    private defaultAccountService: DefaultaccountService
  ) {}

  ngOnInit(): void {
    this.accountService
      .getAllAccounts()
      .pipe(
        catchError((error: HttpErrorResponse) => {
          this.popupMessage = error.error.text;
          this.popupType = false;
          this.isPopupVisible = true;

          setTimeout(() => {
            this.isPopupVisible = false;
          }, 5000);
          return throwError(() => error);
        })
      )
      .subscribe((response: AccountResponse[]) => {
        this.accounts = response;
      });

    this.currencyService.getCurrencies().subscribe((response) => {
      this.currencies = response;
    });

    this.createAccountForm = this.formBuilder.group({
      currency: [''],
      accountName: [''],
      balance: [''],
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

    this.accountService
      .createAccount(request)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          this.popupMessage = error.error.text;
          this.popupType = false;
          this.isPopupVisible = true;

          setTimeout(() => {
            this.isPopupVisible = false;
          }, 5000);
          return throwError(() => error);
        })
      )
      .subscribe((response: AccountResponse) => {
        this.popupMessage = 'Account is successfully created.';
        this.popupType = true;
        this.isPopupVisible = true;
        this.accounts.push(response);

        this.createAccountForm.reset();
        this.defaultSelectedCurrency = 'eur';
        this.createAccountBtnClicked = false;

        setTimeout(() => {
          this.isPopupVisible = false;
        }, 5000);
      });
  }
}
