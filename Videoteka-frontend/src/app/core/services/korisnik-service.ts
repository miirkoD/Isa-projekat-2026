import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Korisnik } from '../model/korisnik';

@Injectable({
  providedIn: 'root',
})
export class KorisnikService {
  private apiUrl= 'http://localhost:8080/api/korisnici';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Korisnik[]> {
    return this.http.get<Korisnik[]>(this.apiUrl);
  }

  create(korisnik: Partial<Korisnik>): Observable<Korisnik> {
    return this.http.post<Korisnik>(this.apiUrl, korisnik);
  }

  update(id: number, korisnik: Partial<Korisnik>): Observable<Korisnik> {
    return this.http.put<Korisnik>(`${this.apiUrl}/${id}`, korisnik);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
