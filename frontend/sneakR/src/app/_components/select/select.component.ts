import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-select',
  standalone: true,
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css'],
})
export class SelectComponent {
  constructor(private router: Router) {}

  navigateTo(target: string) {
    // Kis várakozás animáció előtt, ha szükséges
    setTimeout(() => {
      this.router.navigate([`/${target}`]);
    }, 300);
  }
}
