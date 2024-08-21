import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { WhichAction } from '../models/which-action.model';
import { ActionWithVisibility } from '../models/action-with-visibility';

@Injectable({
  providedIn: 'root',
})
export class CommunicationService {
  private actionSource = new Subject<WhichAction>();
  private actionSourceForFooter = new Subject<ActionWithVisibility>();
  action$ = this.actionSource.asObservable();
  actionForFooter$ = this.actionSourceForFooter.asObservable();

  openCommitTransactionModal() {
    this.actionSource.next(WhichAction.COMMIT_TRANSACTION);
  }

  updateFooter() {
    this.actionSource.next(WhichAction.UPDATE_FOOTER);
  }

  openCreateAccountModal() {
    this.actionSource.next(WhichAction.CREATE_ACCOUNT);
  }

  updateAccountsList() {
    this.actionSource.next(WhichAction.UPDATE_ACCOUNTS);
  }

  updateTransactionsList() {
    this.actionSource.next(WhichAction.UPDATE_TRANSACTIONS);
  }

  changeFooter(isVisible: boolean) {
    this.actionSourceForFooter.next({
      action: WhichAction.CHANGE_FOOTER,
      isVisible,
    });
  }
}
