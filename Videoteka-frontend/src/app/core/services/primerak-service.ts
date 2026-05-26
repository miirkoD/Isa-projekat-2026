import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class PrimerakService {

  private apiUrl = 'http://localhost:8080/api/primerci'; 
}
