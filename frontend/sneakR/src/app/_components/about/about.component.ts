import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './about.component.html',
  styleUrl: './about.component.css'
})
export class AboutComponent {
  cartCount = 2; 
  isMenuActive = false;

  constructor(private router: Router) {}

  navigateToTarget3() {
    this.router.navigate(['/select']);
  }

  toggleMenu() {
    this.isMenuActive = !this.isMenuActive;
  }
}
