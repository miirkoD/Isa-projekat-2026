import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Clan } from '../model/clan';

@Injectable({
  providedIn: 'root',
})
export class ClanService {

  private apiUrl= 'http://localhost:8080/api/clanovi';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Clan[]> {
    return this.http.get<Clan[]>(this.apiUrl);
  }

  getById(id: number): Observable<Clan> {
    return this.http.get<Clan>(`${this.apiUrl}/${id}`);
  }

  create(clan: Partial<Clan>): Observable<Clan> {
    return this.http.post<Clan>(this.apiUrl, clan);
  }

  update(id: number, clan: Partial<Clan>): Observable<Clan> {
    return this.http.put<Clan>(`${this.apiUrl}/${id}`, clan);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
