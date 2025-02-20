import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-resell',
  standalone: true,
  imports: [NavbarComponent,RouterLink],
  templateUrl: './resell.component.html',
  styleUrl: './resell.component.css'
})
export class ResellComponent {

}
