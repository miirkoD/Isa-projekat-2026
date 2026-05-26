import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';
import { tap } from 'rxjs/internal/operators/tap';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
   private apiUrl="http://localhost:8080/api/auth"

  constructor(private http:HttpClient, private router:Router){}

  login(username:string,password:string):Observable<any>{
    return this.http.post<any>(`${this.apiUrl}/login`,{username,password}).pipe(
      tap(response=>{
        localStorage.setItem('token',response.token);
        localStorage.setItem('rola',response.rola);
      })
    );
  }

  logout():void{
    localStorage.removeItem('token');
    localStorage.removeItem('rola');
    this.router.navigate(['/login']);
  }

  getToken():string | null{
    return localStorage.getItem('token');
  }

  getRola():string | null{
    return localStorage.getItem('rola');
  }

  isLoggedIn():boolean{
    return this.getToken()!==null;
  }

  isAdmin():boolean{
    return this.getRola()==='ADMIN';
  }
}
