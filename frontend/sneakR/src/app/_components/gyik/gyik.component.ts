import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-gyik',
  standalone: true,
  imports: [RouterLink, CommonModule,RouterModule],
  templateUrl: './gyik.component.html',
  styleUrl: './gyik.component.css'
})
export class GYIKComponent {
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
