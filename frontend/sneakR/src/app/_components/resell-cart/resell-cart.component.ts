import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { ResellCartService, CartProduct } from '../../_services/resell-cart.service';
import { Router } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-resell-cart',
  standalone: true,
  imports: [NavbarComponent, CommonModule, FormsModule],
  templateUrl: './resell-cart.component.html',
  styleUrls: ['./resell-cart.component.css']
})
export class ResellCartComponent implements OnInit {
  cart: CartProduct[] = [];
  showCard = false;
  showSuccessModal = false;


  constructor(
    private cartService: ResellCartService,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.cartService.getCart().subscribe(cart => {
      this.cart = cart;
    });
  }

  removeItem(productId: number): void {
    this.cartService.removeFromCart(productId);
  }

  showCardDetails(paymentMethod: string): void {
    this.showCard = paymentMethod === 'bankkártya';
  }

  getTotal(): number {
    return this.cart.reduce((acc, item) => acc + item.price, 0);
}

submitCheckout(form: NgForm, event: Event): void {
  event.preventDefault();
  if (form.valid && this.cart.length > 0) {
    this.cartService.clearCart();
    this.showSuccessModal = true;
  }
}

onCloseSuccessModal(): void {
  this.showSuccessModal = false;
  this.router.navigate(['/resell']);
}
}