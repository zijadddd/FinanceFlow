import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AccountResponse } from '../shared/models/account.model';
import { AccountService } from '../shared/services/account.service';
import { provideAnimations } from '@angular/platform-browser/animations';
import { NgFor, NgIf } from '@angular/common';
import { PopupComponent } from '../popup/popup.component';
import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { CommunicationService } from '../shared/services/communication.service';
import { Title } from '@angular/platform-browser';
import { WhichAction } from '../shared/models/which-action.model';
import { LoaderComponent } from '../loader/loader.component';

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [NgFor, NgIf, PopupComponent, LoaderComponent],
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
  public isLoaderVisible: boolean = false;

  constructor(
    private accountService: AccountService,
    private communicationService: CommunicationService,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.isLoaderVisible = true;
    this.titleService.setTitle('FinanceFlow - Accounts');

    this.accountService.getAllAccounts().subscribe(
      (response: AccountResponse[]) => {
        this.accounts = response;
        this.isLoaderVisible = false;
      },
      (error: HttpErrorResponse) => {
        this.isLoaderVisible = false;
      }
    );

    this.communicationService.action$.subscribe((action) => {
      if (action === WhichAction.UPDATE_ACCOUNTS) {
        this.updateAccountsList();
      }
    });
  }

  createAccountModalOpen() {
    this.communicationService.openCreateAccountModal();
  }

  updateAccountsList() {
    this.accountService.getAllAccounts().subscribe((response) => {
      this.accounts = response;
    });
  }
}
