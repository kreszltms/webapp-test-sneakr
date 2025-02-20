// webshop.component.ts
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ShoeService } from '../../_services/shoe.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';
import { CartService, CartProduct } from '../../_services/cart.service';

@Component({
  selector: 'app-webshop',
  standalone: true,
  imports: [CommonModule, HttpClientModule, RouterLink,],
  templateUrl: './webshop.component.html',
  styleUrls: ['./webshop.component.css']
})
export class WebshopComponent implements OnInit {
  isMenuOpen = false;
  newShoes: any[] = [];
  bestSellerShoes: any[] = [];
  menuState: { [key: string]: boolean } = {};
  loading = true;
  cartCount = 0;
  cartItems: CartProduct[] = [];
  showCartMenu = false;

  selectedProduct: any = null;
  selectedSize: string | null = null;
  sizes: string[] = ['36', '37', '38', '39', '40', '41', '42', '43', '44', '45'];

  constructor(
    private shoeService: ShoeService,
    private cartService: CartService,
    private router: Router // Service injektálás
  ) {}

  @ViewChild('best') bestSection!: ElementRef;
  ngOnInit() {
    this.shoeService.getShoes().subscribe({
      next: (shoes) => {
        const half = Math.ceil(shoes.length / 2);
        this.newShoes = shoes.slice(0, half);
        this.bestSellerShoes = shoes.slice(half);
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading shoes:', err);
        this.loading = false;
      }
    });
    this.cartService.getCart().subscribe(cart => {
      this.cartItems = cart;
      this.cartCount = cart.length;
    });
  }

  toggleCartMenu() {
    this.showCartMenu = !this.showCartMenu;
  }

  removeItem(productId: number) {
    this.cartService.removeFromCart(productId);
  }




  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
    if (this.isMenuOpen) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = 'auto';
      this.menuState = {}; // Reset submenü állapotok
    }
  }

  toggleSubmenu(menuKey: string) {
    this.menuState[menuKey] = !this.menuState[menuKey];
  }

  scrollToBest() {
    if (this.bestSection) {
      this.bestSection.nativeElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
  }

  openProductModal(product: any) {
    this.selectedProduct = product;
    this.selectedSize = null;
    document.body.style.overflow = 'hidden';
  }

  closeProductModal() {
    this.selectedProduct = null;
    document.body.style.overflow = 'auto';
  }

  selectSize(size: string) {
    this.selectedSize = size;
  }

  addToCart() {
    if (this.selectedProduct && this.selectedSize) {
      const cartProduct: CartProduct = {
        id: this.selectedProduct.id,
        name: this.selectedProduct.name,
        brand: this.selectedProduct.brand,
        price: this.selectedProduct.price,
        image: this.selectedProduct.image,
        sizes: [Number(this.selectedSize)]
      };
      this.cartService.addToCart(cartProduct);
      this.closeProductModal();
    }
  }
  navigateToTarget3() {
    this.router.navigate(['/select']);
  }
}
