import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface ResellShoe {
  id: number;
  nev: string;
  marka: string;
  nem: string;
  allapot: string;
  meret: number;
  ar: number;
  img: string;
  user_id: number;
}

interface ResellShoesResponse {
  ResellShoes: ResellShoe[];
  statusCode: number;
}

@Injectable({
  providedIn: 'root'
})
export class ResellProductService {
  private apiUrl = 'http://127.0.0.1:8080/sneakRproject-1.0-SNAPSHOT/webresources/resellCipok/getAllResellShoesData';

  constructor(private http: HttpClient) { }

  getResellShoes(): Observable<ResellShoesResponse> {
    return this.http.get<ResellShoesResponse>(this.apiUrl);
  }
}