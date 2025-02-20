// admin.component.ts
import { Component } from '@angular/core';
import { UserService } from '../../_services/user.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { User } from '../../models/user.model';
import { UserResponse } from '../../models/user.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  users: User[] = [];
  isAdmin: boolean = false;
  currentUser: any;
  
  constructor(private userService: UserService, private router: Router) {}

  ngOnInit() {
    const userData = localStorage.getItem('currentUser');
    if (userData) {
      this.currentUser = JSON.parse(userData);
      this.isAdmin = this.currentUser.admin === 'igen';
      
      if (this.isAdmin) {
        this.loadUsers();
      }
    } else {
      this.router.navigate(['/login']);
    }
  }
  

  goBack() {
    this.router.navigate(['/select']);
  }

  loadUsers() {
    this.userService.getAllUsers().subscribe({
      next: (response: UserResponse) => {
        this.users = response.users; // Ha a válaszban "data" szerepel, írd át erre: response.data
        console.log("Felhasználók:", this.users); // Ellenőrizd a konzolon
      },
      error: (err) => console.error('Hiba:', err)
    });
  }

  deleteUser(userId: number) {
    if (confirm('Biztosan törölni szeretné ezt a felhasználót?')) {
      this.userService.deleteUser(userId).subscribe({
        next: () => {
          this.users = this.users.filter(user => user.id !== userId); // Azonnal frissítsd a listát
          console.log('Felhasználó törölve.');
        },
        error: (err) => console.error('Hiba a törléskor:', err)
      });
    }
  }
}