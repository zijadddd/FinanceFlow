import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { WhichAction } from '../models/which-action.model';

@Injectable({
  providedIn: 'root',
})
export class CommunicationService {
  private actionSource = new Subject<WhichAction>();
  action$ = this.actionSource.asObservable();

  openCommitTransactionModal() {
    this.actionSource.next(WhichAction.COMMIT_TRANSACTION);
  }

  updateFooter() {
    this.actionSource.next(WhichAction.UPDATE_FOOTER);
  }

  openCreateAccountModal() {
    console.log('usao 2');
    this.actionSource.next(WhichAction.CREATE_ACCOUNT);
  }
}
