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
}
