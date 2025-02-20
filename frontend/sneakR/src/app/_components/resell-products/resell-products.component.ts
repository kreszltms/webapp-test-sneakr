import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ResellCartService, CartProduct } from '../../_services/resell-cart.service';
import { ResellProductService } from '../../_services/resell-product.service';
import { UserService } from '../../_services/user.service';

interface Product {
  id: number;
  nev: string;
  marka: string;
  nem: string;
  allapot: string;
  meret: number;
  ar: number;
  img: string;
  user_id: number;
  seller?: string;
}

@Component({
  selector: 'app-resell-products',
  standalone: true,
  imports: [NavbarComponent, FormsModule, CommonModule],
  templateUrl: './resell-products.component.html',
  styleUrls: ['./resell-products.component.css']
})
export class ResellProductsComponent implements OnInit {
  originalProducts: Product[] = [];
  filteredProducts: Product[] = [];
  users: any[] = [];
  
  brands: string[] = [];
  sizes: number[] = Array.from({length: 13}, (_, i) => 35 + i);
  selectedBrand: string = '';
  selectedGender: string = '';
  selectedCondition: string = '';
  selectedSize: number | null = null;
  maxPrice: number = 200000;
  originalMaxPrice: number = 200000;

  constructor(
    private cartService: ResellCartService,
    private route: ActivatedRoute,
    private resellService: ResellProductService,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.loadData();
    this.route.queryParams.subscribe(params => {
      this.selectedBrand = params['brand'] || '';
      this.selectedGender = params['gender'] || '';
      this.selectedCondition = params['condition'] || '';
      this.selectedSize = params['size'] ? Number(params['size']) : null;
      this.maxPrice = params['maxPrice'] ? Number(params['maxPrice']) : 200000;
      this.applyFilters();
    });
  }

  private loadData() {
    this.resellService.getResellShoes().subscribe({
      next: (response) => {
        this.originalProducts = response.ResellShoes;
        this.brands = [...new Set(this.originalProducts.map(p => p.marka))];
        this.originalMaxPrice = Math.max(...this.originalProducts.map(p => p.ar));
        this.maxPrice = this.originalMaxPrice;
        this.filteredProducts = [...this.originalProducts];
        this.mapUserNames();
      },
      error: (err) => console.error('Error loading shoes:', err)
    });

    this.userService.getAllUsers().subscribe({
      next: (response) => {
        this.users = response.users;
        this.mapUserNames();
      },
      error: (err) => console.error('Error loading users:', err)
    });
  }

  private mapUserNames() {
    if (this.users.length && this.originalProducts.length) {
      const userMap = new Map(this.users.map(user => [user.id, user.nev]));
      this.originalProducts.forEach(product => {
        product.seller = userMap.get(product.user_id) || 'Ismeretlen eladó';
      });
    }
  }

  addToCart(product: Product) {
    const cartProduct: CartProduct = {
      id: product.id,
      name: product.nev,
      brand: product.marka,
      price: product.ar,
      imgUrl: product.img, // FONTOS: 'img' -> 'imgUrl' leképezés
      seller: product.seller || '',
      condition: product.allapot,
      size: product.meret
    };
    this.cartService.addToCart(cartProduct);
  }

  // ... (applyFilters és resetFilters változatlan)


  applyFilters() {
    this.filteredProducts = this.originalProducts.filter(product =>
      (this.selectedBrand ? product.marka === this.selectedBrand : true) &&
      (this.selectedGender ? product.nem === this.selectedGender : true) &&
      (this.selectedCondition ? product.allapot === this.selectedCondition : true) &&
      (this.selectedSize ? product.meret === this.selectedSize : true) &&
      (product.ar <= this.maxPrice)
    );
  }

  resetFilters() {
    this.selectedBrand = '';
    this.selectedGender = '';
    this.selectedCondition = '';
    this.selectedSize = null;
    this.maxPrice = this.originalMaxPrice;
    this.filteredProducts = [...this.originalProducts];
  }

  
}