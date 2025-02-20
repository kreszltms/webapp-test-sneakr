import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-password',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent {
  email: string = ''; // Az email mező tartalmának tárolása
  isFormValid: boolean = false; // A gomb állapota

  constructor(private router: Router) {}

  checkInput(): void {
    // Az email mező validálása
    const emailInput = document.getElementById('email') as HTMLInputElement;
    this.isFormValid = emailInput?.checkValidity() ?? false;

    // A gomb engedélyezése vagy tiltása
    const submitBtn = document.getElementById('submit-btn') as HTMLButtonElement;
    submitBtn.disabled = !this.isFormValid;
  }

  onSubmit(event: Event): void {
    event.preventDefault(); // Alapértelmezett form-küldés megakadályozása
    alert('A jelszó visszaállító emailt elküldtük a megadott email címre.'); // Üzenet megjelenítése
    this.router.navigate(['/login']); // Átirányítás a login komponensre
  }

  onCancel(): void {
    this.router.navigate(['/login']); // Átirányítás a login komponensre
  }
}
