import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AccountResponse } from '../shared/models/account.model';
import { AccountService } from '../shared/services/account.service';
import { provideAnimations } from '@angular/platform-browser/animations';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [NgFor, NgIf],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css',
  encapsulation: ViewEncapsulation.ShadowDom,
  providers: [provideAnimations()]
})
export class AccountsComponent implements OnInit {
  public accounts: AccountResponse[];

  constructor(private accountService: AccountService) {
  }

  ngOnInit(): void {
    this.accountService.getAllAccounts().subscribe((response: AccountResponse[]) => {
      this.accounts = response;
    });
  }
}
