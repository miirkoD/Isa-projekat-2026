import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Primerak } from '../model/primerak';

@Injectable({
  providedIn: 'root',
})
export class PrimerakService {

  private apiUrl = 'http://localhost:8080/api/primerci'; 

  constructor(private http: HttpClient) {}
  
  getAll(): Observable<Primerak[]>{
      return this.http.get<Primerak[]>(this.apiUrl);
    }
  
    getById(id:number): Observable<Primerak>{
      return this.http.get<Primerak>(`${this.apiUrl}/${id}`);
    }
  
    create(primerak:Partial<Primerak>): Observable<Primerak>{
      return this.http.post<Primerak>(this.apiUrl, primerak);
    }
    
    update(id:number, primerak:Partial<Primerak>): Observable<Primerak>{
      return this.http.put<Primerak>(`${this.apiUrl}/${id}`, primerak);
    }
  
    delete(id:number): Observable<void>{
      return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
  
}
