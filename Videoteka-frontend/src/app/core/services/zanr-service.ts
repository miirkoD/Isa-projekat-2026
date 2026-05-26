import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root',
})
export class ZanrService {

  private apiUrl= 'http://localhost:8080/api/zanrovi';

  constructor(private http: HttpClient) {}

  getAll(): Observable<any[]>{
    return this.http.get<any[]>(this.apiUrl);
  }

  getById(id:number): Observable<any>{
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }
}
