import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterLink, FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  email: string = '';
  password: string = '';
  nev: string = '';
  isFormValid: boolean = false;
  emailValid: boolean = true;
  passwordValid: boolean = true;
  nevValid: boolean = true;
  submitting: boolean = false;

  constructor(private router: Router, private http: HttpClient) {}

  private isValidEmail(email: string): boolean {
    const emailRegex = /^[a-zA-Z0-9.%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    return emailRegex.test(email);
  }

  private isValidPassword(password: string): boolean {
    if (password.length < 8) return false;
    let hasNumber = false, hasUpperCase = false, hasLowerCase = false, hasSpecialChar = false;
    for (const c of password) {
      if (/\d/.test(c)) hasNumber = true;
      else if (c === c.toUpperCase()) hasUpperCase = true;
      else if (c === c.toLowerCase()) hasLowerCase = true;
      if ("!@#$%^&*()_+-=[]{}|;':,.<>?/`~".includes(c)) hasSpecialChar = true;
    }
    return hasNumber && hasUpperCase && hasLowerCase && hasSpecialChar;
  }

  checkInputs(): void {
    this.emailValid = this.isValidEmail(this.email);
    this.passwordValid = this.isValidPassword(this.password);
    this.nevValid = this.nev.trim() !== '';
    this.isFormValid = this.emailValid && this.passwordValid && this.nevValid;
  }

  onSubmit(event: Event): void {
    event.preventDefault();
    if (this.submitting || !this.isFormValid) return;
    this.submitting = true;

    const userData = {
      nev: this.nev,
      email: this.email,
      password: this.password
    };

    this.http.post<{ status: string, statusCode: number }>(
      'http://127.0.0.1:8080/sneakRproject-1.0-SNAPSHOT/webresources/userek/registerUser',
      userData
    ).subscribe({
      next: (response) => {
        this.submitting = false;
        if (response.status === 'success') {
          alert('Sikeres regisztráció! Üdvözlünk a SneakR közösségben.');
          this.router.navigate(['/login']);
        } else {
          this.handleErrorResponse(response);
        }
      },
      error: (error: HttpErrorResponse) => {
        this.submitting = false;
        this.handleHttpError(error);
      }
    });
  }

  private handleErrorResponse(response: { status: string }): void {
    let errorMessage = 'Hiba történt a regisztráció során.';
    switch (response.status) {
      case 'InvalidEmail':
        errorMessage = 'Érvénytelen email cím.';
        break;
      case 'InvalidPassword':
        errorMessage = 'Érvénytelen jelszó. A jelszónak legalább 8 karakter hosszúnak kell lennie, és tartalmaznia kell számot, kis- és nagybetűt, valamint speciális karaktert!';
        break;
      case 'UserAlreadyExists':
        errorMessage = 'Ez az email cím már regisztrálva van.';
        break;
      case 'RegistrationFailed':
        errorMessage = 'A regisztráció sikertelen. Kérjük, próbálja újra később.';
        break;
    }
    alert(errorMessage);
  }

  private handleHttpError(error: HttpErrorResponse): void {
    let errorMessage = 'Szerverhiba történt. Kérjük, próbálja újra később.';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Hiba történt: ${error.error.message}`;
    } else {
      switch (error.status) {
        case 409:
          errorMessage = 'Ez az email cím már regisztrálva van.';
          break;
        case 400:
          if (error.error.status === 'InvalidEmail') {
            errorMessage = 'Érvénytelen email cím.';
          } else if (error.error.status === 'InvalidPassword') {
            errorMessage = 'Érvénytelen jelszó.';
          }
          break;
      }
    }
    alert(errorMessage);
  }
}