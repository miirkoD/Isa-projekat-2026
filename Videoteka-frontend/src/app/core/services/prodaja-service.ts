import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ProdajaService {

  private apiUrl= 'http://localhost:8080/api/clanovi';

  constructor(private http: HttpClient) {}
}
