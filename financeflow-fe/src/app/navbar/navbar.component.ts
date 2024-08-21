import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommunicationService } from '../shared/services/communication.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent {
  constructor(private communicationService: CommunicationService) {}

  changeFooter(isVisible: boolean) {
    this.communicationService.changeFooter(isVisible);
  }
}
