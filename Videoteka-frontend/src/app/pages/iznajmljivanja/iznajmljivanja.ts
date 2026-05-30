import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Iznajmljivanje } from '../../core/model/iznajmljivanje';
import { Primerak } from '../../core/model/primerak';
import { Clan } from '../../core/model/clan';
import { IznajmljivanjeService } from '../../core/services/iznajmljivanje-service';
import { AuthService } from '../../core/services/auth-service';
import { ClanService } from '../../core/services/clan-service';
import { PrimerakService } from '../../core/services/primerak-service';

@Component({
  selector: 'app-iznajmljivanja',
  imports: [CommonModule,FormsModule],
  templateUrl: './iznajmljivanja.html',
  styleUrl: './iznajmljivanja.css',
})
export class Iznajmljivanja implements OnInit{
iznajmljivanja = signal<Iznajmljivanje[]>([]);
  primerci = signal<Primerak[]>([]);
  clanovi = signal<Clan[]>([]);

  prikaziFormu = false;
  prikaziKasnjenja = false;
  greska = '';
  uspeh = '';

  forma = {
    primerakId: 0,
    clanId: 0,
    rokVracanja: ''
  };

   constructor(
    private iznajmljivanjeService: IznajmljivanjeService,
    private primerakService: PrimerakService,
    private clanService: ClanService,
    public authService: AuthService
  ) {}

  ucitajIznajmljivanja(): void {
    this.iznajmljivanjeService.getAll().subscribe({
      next: (data) => this.iznajmljivanja.set(data),
      error: () => this.greska = 'Greška pri učitavanju iznajmljivanja.'
    });
  }

  ucitajPrimerke(): void {
    this.primerakService.getAll().subscribe({
      next: (data) => {
        this.primerci.set(data.filter(p => p.polovan && p.status === 'DOSTUPAN'));
      }
    });
  }

  ucitajClanove(): void {
    this.clanService.getAll().subscribe({
      next: (data) => this.clanovi.set(data.filter(c => c.aktivan))
    });
  }

  ngOnInit():void{
    this.ucitajIznajmljivanja();
    this.ucitajPrimerke();
    this.ucitajClanove();
  }

  otvoriFormu(): void {
    this.forma = { primerakId: 0, clanId: 0, rokVracanja: '' };
    this.prikaziFormu = true;
    this.greska = '';
    this.uspeh = '';
  }

  iznajmi():void{
    if(!this.forma.primerakId || !this.forma.clanId || !this.forma.rokVracanja){
      this.greska = 'Sva polja su obavezna';
      return;
    }

    const korisnikId=this.authService.getKorisnikId();

    const payload = {
      primerak: { id: this.forma.primerakId },
      clan: { id: this.forma.clanId },
      korisnik: { id: korisnikId },
      rokVracnja: this.forma.rokVracanja
    };
    this.iznajmljivanjeService.iznajmi(payload).subscribe({
      next: () => {
        this.prikaziFormu = false;
        this.uspeh = 'Primerak uspešno iznajmljen.';
        this.ucitajIznajmljivanja();
        this.ucitajPrimerke();
      },
      error: (err) => this.greska = err.error || 'Greška pri iznajmljivanju.'
    });
  }

  vrati(id:number):void{
    if (!confirm('Da li ste sigurni da želite da vratite ovaj primerak?')) return;
    this.iznajmljivanjeService.vrati(id).subscribe({
      next: () => {
        this.uspeh = 'Primerak uspešno vraćen.';
        this.ucitajIznajmljivanja();
        this.ucitajPrimerke();
      },
      error: (err) => this.greska = err.error || 'Greška pri vraćanju primerka.'
    });
  }

  otkaziFormu(): void {
    this.prikaziFormu = false;
    this.greska = '';
  }
}
