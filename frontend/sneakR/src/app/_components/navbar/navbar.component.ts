import { Component, OnInit, OnDestroy, Renderer2 } from '@angular/core';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ResellCartService, CartProduct } from '../../_services/resell-cart.service'; // Új import
import { Subscription } from 'rxjs'; // Új import

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnDestroy {
  cartCount = 0;
  isMenuActive = false;
  qualityMenuActive = false;
  brandMenuActive = false;
  brands = ['Jordan', 'Nike', 'Adidas', 'Yeezy', 'New Balance', 'Alexander McQueen', 'Travis Scott', 'Reebok', 'Converse', 'Puma'];
  private cartSubscription!: Subscription; // Új változó
  showCartMenu = false;
  cartItems: CartProduct[] = [];

  constructor(
    private router: Router,
    public cartService: ResellCartService, // Service injektálás
    private renderer: Renderer2
  ) {}

  ngOnInit() {
    this.cartService.getCart().subscribe(cart => {
      this.cartItems = [...cart];  // Frissítjük a tényleges terméklistát
      this.cartCount = cart.length;
    });
  }

  toggleCartMenu() {
    this.showCartMenu = !this.showCartMenu;
  }

  navigateToCart() {
    this.showCartMenu = false;
    this.router.navigate(['/resell-cart']);
  }

  ngOnDestroy() {
    // Leiratkozás a memóriaszivárgás elkerüléséért
    if (this.cartSubscription) {
      this.cartSubscription.unsubscribe();
    }
  }

  // ... (egyéb metódusok változatlanok)

  
  navigateToTarget3() {
    this.router.navigate(['/select']);
  }

  
  toggleMenu() {
    this.isMenuActive = !this.isMenuActive;
  }

  toggleQualityMenu() {
    this.qualityMenuActive = !this.qualityMenuActive;
  }

  toggleBrandMenu() {
    this.brandMenuActive = !this.brandMenuActive;
  }

  filterByCategory(category: string) {
    this.router.navigate(['/resell-products'], { queryParams: { gender: category } });
    this.closeMenu();
  }

  filterByQuality(quality: string) {
    this.router.navigate(['/resell-products'], { queryParams: { condition: quality } });
    this.closeMenu();
  }

  filterByBrand(brand: string) {
    this.router.navigate(['/resell-products'], { queryParams: { brand: brand } });
    this.closeMenu();
  }

  private closeMenu() {
    this.isMenuActive = false;
  }

  // A komponens osztályba illeszd ezt a metódust
removeItem(productId: number): void {
  this.cartService.removeFromCart(productId);
}
}