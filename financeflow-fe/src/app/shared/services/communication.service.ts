import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CommunicationService {
  private actionSource = new Subject<any>();
  action$ = this.actionSource.asObservable();

  callMethod() {
    this.actionSource.next(null);
  }
}
