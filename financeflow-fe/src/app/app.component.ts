import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { AccountsComponent } from './accounts/accounts.component';
import { FooterComponent } from './footer/footer.component';
import { CommonModule } from '@angular/common';
import { TransactionModalComponent } from './transaction-modal/transaction-modal.component';
import { AccountModalComponent } from './account-modal/account-modal.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    NavbarComponent,
    AccountsComponent,
    FooterComponent,
    TransactionModalComponent,
    AccountModalComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'financeflow-fe';
}
