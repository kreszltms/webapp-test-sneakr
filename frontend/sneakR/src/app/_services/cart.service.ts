import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface CartProduct {
  id: number;
  name: string;
  brand: string;
  price: number;
  image: string;
  sizes: number[];
}

@Injectable({ providedIn: 'root' })
export class CartService {
  private cartItems: CartProduct[] = [];
  private cartSubject = new BehaviorSubject<CartProduct[]>([]);

  getCart() {
    return this.cartSubject.asObservable();
  }

  addToCart(product: CartProduct) {
    this.cartItems = [...this.cartItems, product];
    this.cartSubject.next(this.cartItems);
  }

  removeFromCart(productId: number) {
    this.cartItems = this.cartItems.filter(item => item.id !== productId);
    this.cartSubject.next(this.cartItems);
  }

  clearCart() {
    this.cartItems = [];
    this.cartSubject.next([]);
  }

  getTotal() {
    return this.cartItems.reduce((acc, item) => acc + item.price, 0);
  }
}