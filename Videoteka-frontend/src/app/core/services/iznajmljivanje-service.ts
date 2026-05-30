import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Iznajmljivanje } from '../model/iznajmljivanje';

@Injectable({
  providedIn: 'root',
})
export class IznajmljivanjeService {

  private apiUrl= 'http://localhost:8080/api/iznajmljivanja';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Iznajmljivanje[]> {
    return this.http.get<Iznajmljivanje[]>(this.apiUrl);
  }

  iznajmi(data: any): Observable<Iznajmljivanje> {
    return this.http.post<Iznajmljivanje>(`${this.apiUrl}/iznajmi`, data);
  }

  vrati(id: number): Observable<Iznajmljivanje> {
    return this.http.put<Iznajmljivanje>(`${this.apiUrl}/vrati/${id}`, {});
  }

  getKasnjenja(): Observable<Iznajmljivanje[]> {
    return this.http.get<Iznajmljivanje[]>(`${this.apiUrl}/kasnjenja`);
  }

  getIstorijaClana(clanId: number): Observable<Iznajmljivanje[]> {
    return this.http.get<Iznajmljivanje[]>(`${this.apiUrl}/clan/${clanId}`);
  }
}
