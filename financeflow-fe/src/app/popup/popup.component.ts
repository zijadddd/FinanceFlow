import { NgIf, NgStyle } from '@angular/common';
import {
  Component,
  Input,
  OnInit,
  OnChanges,
  SimpleChanges,
} from '@angular/core';

@Component({
  selector: 'app-popup',
  standalone: true,
  imports: [NgIf, NgStyle],
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css'],
})
export class PopupComponent implements OnInit, OnChanges {
  @Input() message: string;
  @Input() isVisible: boolean;
  @Input() type: boolean;

  backgroundColor: string;

  ngOnInit() {
    this.updateBackgroundColor();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['type']) {
      this.updateBackgroundColor();
    }
  }

  private updateBackgroundColor() {
    this.backgroundColor = this.type ? '#49CA3E' : '#d32f2f';
  }
}
