import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Prodaja } from '../model/prodaja';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProdajaService {

  private apiUrl= 'http://localhost:8080/api/prodaje';

  constructor(private http: HttpClient) {}
  
  getAll(): Observable<Prodaja[]> {
    return this.http.get<Prodaja[]>(this.apiUrl);
  }

  prodaj(data: any): Observable<Prodaja> {
    return this.http.post<Prodaja>(`${this.apiUrl}/prodaj`, data);
  }

  getIstorijaClana(clanId: number): Observable<Prodaja[]> {
    return this.http.get<Prodaja[]>(`${this.apiUrl}/clan/${clanId}`);
  }
}
