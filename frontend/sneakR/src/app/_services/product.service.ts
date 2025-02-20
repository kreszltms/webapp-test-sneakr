import { Injectable } from '@angular/core';

export interface Product {
  id: number;
  name: string;
  brand: string;
  model?: string;
  price: number;
  sizes: number[];
  image: string;
  category?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ProductService {
}