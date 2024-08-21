import { Component, OnInit } from '@angular/core';
import { provideAnimations } from '@angular/platform-browser/animations';
import { DefaultaccountService } from '../shared/services/defaultaccount.service';
import { CommunicationService } from '../shared/services/communication.service';
import { NgFor, NgIf } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PopupComponent } from '../popup/popup.component';
import { WhichAction } from '../shared/models/which-action.model';
import { ActionWithVisibility } from '../shared/models/action-with-visibility';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [NgIf, NgFor, PopupComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css',
  providers: [provideAnimations()],
})
export class FooterComponent implements OnInit {
  public balance: number;
  public currencyCode: string;
  public isSettingsPage: boolean = false;

  constructor(
    private defaultAccountService: DefaultaccountService,
    private communicationService: CommunicationService
  ) {}

  ngOnInit(): void {
    this.getDefaultAccount();

    this.communicationService.action$.subscribe((action) => {
      if (action === WhichAction.UPDATE_FOOTER) {
        this.getDefaultAccount();
      }
    });

    this.communicationService.actionForFooter$.subscribe(
      (actionWithVisibility: ActionWithVisibility) => {
        if (actionWithVisibility.action === WhichAction.CHANGE_FOOTER) {
          this.isSettingsPage = actionWithVisibility.isVisible ?? false;
        }
      }
    );
  }

  getDefaultAccount() {
    this.defaultAccountService.getDefaultAccount().subscribe((response) => {
      this.balance = response.balance;
      this.currencyCode = response.currencyCode;
    });
  }

  commitTransactionModalOpen() {
    this.communicationService.openCommitTransactionModal();
  }
}
