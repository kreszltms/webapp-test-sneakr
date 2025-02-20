import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [RouterLink, CommonModule, RouterModule],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent {
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
