import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../../_services/product.service';
import { ProductService } from '../../_services/product.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { WebshopnavbarComponent } from '../webshopnavbar/webshopnavbar.component';
import { CartService } from '../../_services/cart.service';
import { ShoeService } from '../../_services/shoe.service';

@Component({
  standalone: true,
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css'],
  imports: [CommonModule, FormsModule, WebshopnavbarComponent],
})
export class ProductsComponent implements OnInit {
  products: Product[] = [];
  filteredProducts: Product[] = [];

  absoluteMinPrice: number = 0;
  absoluteMaxPrice: number = 200000;
  availableSizes: number[] = [];
  baseProducts: Product[] = [];
  selectedSize: string = '';
  minPrice: number = 0;
  maxPrice: number = 1000000; // vagy bármilyen magas érték, pl. 200000

  constructor(
    private route: ActivatedRoute,
    private shoeService: ShoeService,
    private cartService: CartService
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.shoeService.getShoes().subscribe(products => {
        this.products = products;
        this.applyQueryParamsFilters(params);
      });
    });
  }

  private applyQueryParamsFilters(params: any) {
    // Filter by query params first
    this.baseProducts = this.products.filter(product => {
      let match = true;
      if (params['brand']) match = match && product.brand === params['brand'];
      if (params['model']) match = match && product.model === params['model'];
      if (params['category']) match = match && product.category === params['category'];
      return match;
    });

    // Update available sizes and price range
    this.updateAvailableSizes();
    this.updatePriceRange();
    
    // Apply filters
    this.applyFilters();
  }

  private updateAvailableSizes() {
    const sizes = new Set<number>();
    this.baseProducts.forEach(product => {
      product.sizes.forEach(size => sizes.add(size));
    });
    this.availableSizes = Array.from(sizes).sort((a, b) => a - b);
  }

  private updatePriceRange() {
    if (this.baseProducts.length > 0) {
      this.absoluteMinPrice = Math.min(...this.baseProducts.map(p => p.price));
      this.absoluteMaxPrice = Math.max(...this.baseProducts.map(p => p.price));
      this.minPrice = this.absoluteMinPrice;
      this.maxPrice = this.absoluteMaxPrice;
    }
  }

  applyFilters() {
    this.filteredProducts = this.baseProducts.filter(product => {
      const matchesSize = !this.selectedSize || product.sizes.includes(+this.selectedSize);
      const matchesPrice = product.price >= this.minPrice && product.price <= this.maxPrice;
      return matchesSize && matchesPrice;
    });
  }
  getTitle(): string {
    return (
      this.route.snapshot.queryParams['brand'] ||
      this.route.snapshot.queryParams['model'] ||
      this.route.snapshot.queryParams['category'] ||
      'Összes termék'
    );
  }
  addToCart(product: Product) {
    this.cartService.addToCart(product);
  
}
}
