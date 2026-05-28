import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Primerak } from '../../core/model/primerak';
import { Film } from '../../core/model/film';
import { PrimerakService } from '../../core/services/primerak-service';
import { FilmService } from '../../core/services/film-service';
import { AuthService } from '../../core/services/auth-service';

@Component({
  selector: 'app-primerci',
  imports: [CommonModule,FormsModule],
  templateUrl: './primerci.html',
  styleUrl: './primerci.css',
})
export class Primerci implements OnInit{
  primerci=signal<Primerak[]>([]);
  filmovi= signal<Film[]>([]);

  prikaziFormu=false;
  editMode=false;
  greska="";

  forma: Partial<Primerak> ={
    film:{id:0, naslov:'',zanr:{id:0, naziv:''}},
    polovan:false,
    status:'DOSTUPAN',
    cenaIznajmljivanja:undefined,
    cenaProdaje:undefined
  };

  constructor(private primerakService:PrimerakService, private filmService:FilmService, public authService:AuthService){}

  ngOnInit():void{
    this.ucitajPrimerke();
    this.ucitajFilmove();
  }

  ucitajPrimerke():void{
    this.primerakService.getAll().subscribe({
      next:(data)=> this.primerci.set(data),
      error:(err)=> (this.greska="Neuspešno učitavanje primeraka" + err.message)
    })
  }
  
  ucitajFilmove():void{
    this.filmService.getAll().subscribe({
      next:(data)=> this.filmovi.set(data),
      error:(err)=>(this.greska="Neuspešno učitavanje filmova" + err.message)
    })
  }

  stanjeNaziv(polovan:boolean):string{
    return polovan ? "Polovan" : "Nov";
  }

  otvoriFormuZaDodavanje():void{
    this.editMode=false;
    this.forma={
      film:{id:0, naslov:'',zanr:{id:0, naziv:''}},
      polovan:false,
      status:'DOSTUPAN',
      cenaIznajmljivanja:undefined,
      cenaProdaje:undefined
    };
    this.prikaziFormu=true;
    this.greska='';
  }

  otvoriFormuZaIzmenu(primerak:Primerak):void{
    this.editMode=true;
    this.forma={
      id:primerak.id,
      film:{id:primerak.film!.id, naslov:primerak.film!.naslov, zanr:{id:0, naziv:''}},
      polovan:primerak.polovan,
      status:primerak.status,
      cenaIznajmljivanja:primerak.cenaIznajmljivanja,
      cenaProdaje:primerak.cenaProdaje
    };
    this.prikaziFormu=true;
    this.greska='';
  }

  sacuvaj():void{
    if(!this.forma.film?.id){
      this.greska='Film je obavezan';
      return;
    }

    const payload:Partial<Primerak>={
      film:{id:this.forma.film.id, naslov:'', zanr:{id:0, naziv:''}},
      polovan:this.forma.polovan,
      status:this.forma.status,
      cenaIznajmljivanja: this.forma.cenaIznajmljivanja,
      cenaProdaje: this.forma.cenaProdaje
    };

    if(this.editMode && this.forma.id !== undefined){
      this.primerakService.update(this.forma.id, payload).subscribe({
        next:(data)=>{this.prikaziFormu=false; this.ucitajPrimerke();},
        error:(err)=> this.greska="Neuspešno ažuriranje primerka"
      })
    }else{
      this.primerakService.create(payload).subscribe({
        next:(data)=>{this.prikaziFormu=false; this.ucitajPrimerke();},
        error:(err)=> this.greska="Neuspešno kreiranje primerka"
      })
    }
  }

  obrisi(id:number):void{
    if(!confirm('Da li ste sigurni da zelite da obrisete ovaj primerak?')) return;

    this.primerakService.delete(id).subscribe({
      next:()=> this.ucitajPrimerke(),
      error:(err)=> this.greska="Neuspešno brisanje primerka"
    });
  }

  otkaziFormu():void{
    this.prikaziFormu=false;
    this.greska='';
  }
}
  
