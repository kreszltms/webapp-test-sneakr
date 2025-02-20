// login.component.ts
import { Component } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
// Update the import path in login.component.ts
import { LoginService } from '../../_services/login.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpErrorResponse } from '@angular/common/http'; // Added error type


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, RouterOutlet, FormsModule, CommonModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';
  isLoading: boolean = false;

  constructor(private loginService: LoginService, private router: Router) {}

  onSubmit() {
    this.isLoading = true;
    this.errorMessage = '';
    
    if (!this.email || !this.password) {
      this.errorMessage = 'Kérlek töltsd ki mindkét mezőt!';
      this.isLoading = false;
      return;
    }

    if (!this.email) {
      this.errorMessage = 'Kérjük add meg az email címed!';
      this.isLoading = false;
      return;
    }

    if (!this.password) {
      this.errorMessage = 'Kérjük add meg a jelszavad!';
      this.isLoading = false;
      return;
    }

    this.loginService.login(this.email, this.password).subscribe({
      next: (response: any) => {
        if (response.status === 'success') {
          this.router.navigate(['/select']);
        } else {
          this.errorMessage = this.getBackendErrorMessage(response.status);
        }
        this.isLoading = false;
      },
      error: (error: HttpErrorResponse) => {
        this.errorMessage = this.getErrorMessage(error);
        this.isLoading = false;
      }
    });
  }

  private getErrorMessage(error: HttpErrorResponse): string {
  if (error.error instanceof ErrorEvent) {
    return 'Hálózati hiba történt';
  }
  if (error.status === 0) {
    return 'Nem sikerült kapcsolódni a szerverhez';
  }
  if (error.status === 500) {
    return 'Szerverhiba. Kérjük próbálja újra később.';
  }
  return error.error?.message || 'Hibás bejelentkezési adatok';
}

  private getBackendErrorMessage(status: string): string {
    switch (status) {
      case 'invalidEmail':
        return 'Érvénytelen email formátum!';
      case 'userNotFound':
        return 'Hibás email cím vagy jelszó!';
      case 'modelException':
        return 'Szerverhiba. Kérjük próbáld újra később!';
      default:
        return 'Ismeretlen hiba történt!';
    }
  }
}

