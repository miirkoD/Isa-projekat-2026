import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth-service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule,CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  username: string = '';
  password: string = '';
  greska: string = '';

  constructor(private authService:AuthService,private router:Router) {}

  login():void{
    this.authService.login(this.username,this.password).subscribe({
      next:()=>this.router.navigate(['/dashboard']),
      error:()=>this.greska = 'Pogrešno korisničko ime ili lozinka'
    });
  }
}
