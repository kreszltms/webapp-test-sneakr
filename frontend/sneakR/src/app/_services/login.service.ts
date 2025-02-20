// login.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';

interface LoginResponse {
  result: {
    id: number;
    nev: string;
    email: string;
    jelszo: string;
    admin: string;
  };
  status: string;
  statusCode: number;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'https://shark-app-5llz9.ondigitalocean.app:4200/sneakRproject-1.0-SNAPSHOT/sneakRproject-1.0-SNAPSHOT/webresources/userek/login';

  constructor(private http: HttpClient, private router: Router) { }

  login(email: string, password: string) {
  const body = {
    email: email,
    password: password
  };

  return this.http.post<any>(
    'https://shark-app-5llz9.ondigitalocean.app:4200/sneakRproject-1.0-SNAPSHOT/sneakRproject-1.0-SNAPSHOT/webresources/userek/login',
    body,
    {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      observe: 'response'
    }
  ).pipe(
    map(response => {
      if (response.body.status === 'success') {
        localStorage.setItem('currentUser', JSON.stringify(response.body.result));
        this.router.navigate(['/select']);
      }
      return response.body;
    }),
    catchError(error => {
      let errorMessage = 'Ismeretlen hiba történt';
      if (error.status === 500) {
        errorMessage = 'Szerverhiba. Kérjük próbálja újra később.';
      }
      return throwError(() => new Error(errorMessage));
    })
  );
}
}