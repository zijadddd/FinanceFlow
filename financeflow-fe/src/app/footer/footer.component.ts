import { Component, OnInit } from '@angular/core';
import { provideAnimations } from '@angular/platform-browser/animations';
import { DefaultaccountService } from '../shared/services/defaultaccount.service';
import { CommunicationService } from '../shared/services/communication.service';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css',
  providers: [provideAnimations()],
})
export class FooterComponent implements OnInit {
  public balance: number;
  public currencyCode: string;

  constructor(
    private defaultAccountService: DefaultaccountService,
    private communicationService: CommunicationService
  ) {}

  ngOnInit(): void {
    this.getDefaultAccount();

    this.communicationService.action$.subscribe(() => {
      this.getDefaultAccount();
    });
  }

  getDefaultAccount() {
    this.defaultAccountService.getDefaultAccount().subscribe((response) => {
      this.balance = response.balance;
      this.currencyCode = response.currencyCode;
    });
  }
}
