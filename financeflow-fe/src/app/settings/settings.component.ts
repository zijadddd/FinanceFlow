import { encapsulateStyle } from '@angular/compiler';
import { Component, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.css',
  encapsulation: ViewEncapsulation.ShadowDom
})
export class SettingsComponent {

}
