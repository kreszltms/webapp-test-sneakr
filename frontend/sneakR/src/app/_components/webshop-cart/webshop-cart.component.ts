import { Component } from '@angular/core';
import { CartService, CartProduct } from '../../_services/cart.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-webshop-cart',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './webshop-cart.component.html',
  styleUrls: ['./webshop-cart.component.css']
})
export class WebshopCartComponent {
  showCard = false;
  cartItems: CartProduct[] = [];

  constructor(public cartService: CartService) {
    this.cartService.getCart().subscribe(items => {
      this.cartItems = items;
    });
  }

  removeItem(productId: number) {
    this.cartService.removeFromCart(productId);
  }

  showCardDetails(paymentMethod: string) {
    this.showCard = paymentMethod === 'bankkártya';
  }

  checkout() {
    alert('Köszönjük a vásárlást! A rendelését feldolgozzuk.');
    this.cartService.clearCart();
  }
}