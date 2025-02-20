import { CommonModule } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { ResellProductService } from '../../_services/resell-product.service';
import { ResellCartService } from '../../_services/resell-cart.service';

@Component({
  selector: 'app-resell-user',
  standalone: true,
  imports: [RouterLink, RouterOutlet, CommonModule],
  templateUrl: './resell-user.component.html',
  styleUrls: ['./resell-user.component.css']
})
export class ResellUserComponent implements OnInit  {
  
  @ViewChild('currentListings') currentListings!: ElementRef;
  @ViewChild('pastListings') pastListings!: ElementRef;
  @ViewChild('purchasedItems') purchasedItems!: ElementRef;

  user: any = {
    nev: 'Felhasználó',
    email: '',
    activeListingsCount: 2
  };

  userShoes: any[] = [];

  menuOpen: boolean = false;

  itemsPerView = 2; // Show 2 items at a time
currentSlide = 0;

get slides(): any[][] {
  const slides = [];
  for (let i = 0; i < this.userShoes.length; i += this.itemsPerView) {
    slides.push(this.userShoes.slice(i, i + this.itemsPerView));
  }
  return slides;
}

get maxSlides(): number {
  return this.slides.length - 1;
}

prevSlide() {
  this.currentSlide = Math.max(0, this.currentSlide - 1);
}

nextSlide() {
  this.currentSlide = Math.min(this.maxSlides, this.currentSlide + 1);
}

toggleMenu() {
  this.menuOpen = !this.menuOpen;
}

  constructor(
    private router: Router,
    private resellService: ResellProductService,
    private cartService: ResellCartService
  ) {}

  ngOnInit() {
    const userData = localStorage.getItem('currentUser');
    if (userData) {
      this.user = JSON.parse(userData);
      this.loadUserShoes();
    } else {
      this.router.navigate(['/login']);
    }
  }

  private loadUserShoes() {
    this.resellService.getResellShoes().subscribe({
      next: (response) => {
        this.userShoes = response.ResellShoes.filter(
          (shoe: any) => shoe.user_id === this.user.id
        );
        this.user.activeListingsCount = this.userShoes.length;
      },
      error: (err) => console.error('Error loading user shoes:', err)
    });
  }

  
    
  
    deleteListing(shoeId: number, event: Event): void {
    const button = event.target as HTMLElement;
    const card = button.closest('.card');
    
    // Call backend delete endpoint here if implemented
    this.userShoes = this.userShoes.filter(shoe => shoe.id !== shoeId);
    
    if (card) {
      card.classList.add('fade-out');
      setTimeout(() => card.remove(), 300);
    }
  }

  scroll(target: HTMLElement): void {
    target.scrollIntoView({ behavior: 'smooth', block: 'start' });
  }

  
}