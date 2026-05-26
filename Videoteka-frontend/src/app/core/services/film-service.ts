import { Injectable } from '@angular/core';
import { Film } from '../model/film';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root',
})
export class FilmService {

  private apiUrl= 'http://localhost:8080/api/filmovi';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Film[]>{
    return this.http.get<Film[]>(this.apiUrl);
  }

  getById(id:number): Observable<Film>{
    return this.http.get<Film>(`${this.apiUrl}/${id}`);
  }

  create(film:Partial<Film>): Observable<Film>{
    return this.http.post<Film>(this.apiUrl, film);
  }
  
  update(id:number, film:Partial<Film>): Observable<Film>{
    return this.http.put<Film>(`${this.apiUrl}/${id}`, film);
  }

  delete(id:number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  pretrazi(naslov?:string, reziser?:string, zanr?:number): Observable<Film[]>{
    const params:any={};
    if(naslov && naslov.trim()) params['naslov']=naslov;
    if(reziser && reziser.trim()) params['reziser']=reziser;
    if(zanr) params['zanrId']=zanr;
    return this.http.get<Film[]>(`${this.apiUrl}/pretraga`, { params });
  }
}
