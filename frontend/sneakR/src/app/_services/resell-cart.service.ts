import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface CartProduct {
  id: number;
  name: string;
  brand: string;
  price: number;
  imgUrl: string; // ELLENŐRIZD MEG HOGY EZ A PROP NEV HELYES!
  seller: string;
  condition: string;
  size: number;
}

@Injectable({ providedIn: 'root' })
export class ResellCartService {
  private cart: CartProduct[] = [];
  private cartSubject = new BehaviorSubject<CartProduct[]>([]);

  getCart() {
    return this.cartSubject.asObservable();
  }

  addToCart(product: CartProduct) {
    this.cart = [...this.cart, product]; // Új referencia létrehozása
    this.cartSubject.next(this.cart);
  }
  removeFromCart(productId: number) {
    this.cart = this.cart.filter(p => p.id !== productId);
    this.cartSubject.next([...this.cart]);
  }

  clearCart() {
    this.cart = [];
    this.cartSubject.next([]);
  }
}