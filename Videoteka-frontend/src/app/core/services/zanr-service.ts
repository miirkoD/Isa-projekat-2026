import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Zanr } from '../model/zanr';

@Injectable({
  providedIn: 'root',
})
export class ZanrService {

  private apiUrl= 'http://localhost:8080/api/zanrovi';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Zanr[]>{
    return this.http.get<Zanr[]>(this.apiUrl);
  }

  getById(id:number): Observable<Zanr>{
    return this.http.get<Zanr>(`${this.apiUrl}/${id}`);
  }

  create(zanr: Partial<Zanr>): Observable<Zanr> {
  return this.http.post<Zanr>(this.apiUrl, zanr);
}

update(id: number, zanr: Partial<Zanr>): Observable<Zanr> {
  return this.http.put<Zanr>(`${this.apiUrl}/${id}`, zanr);
}

delete(id: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/${id}`);
}
}
