import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Prodaja as ProdajaModel } from '../../core/model/prodaja';
import { Primerak } from '../../core/model/primerak';
import { Clan } from '../../core/model/clan';
import { ProdajaService } from '../../core/services/prodaja-service';
import { ClanService } from '../../core/services/clan-service';
import { PrimerakService } from '../../core/services/primerak-service';
import { AuthService } from '../../core/services/auth-service';

@Component({
  selector: 'app-prodaja',
  imports: [CommonModule, FormsModule],
  templateUrl: './prodaja.html',
  styleUrl: './prodaja.css',
})
export class Prodaja implements OnInit {
  prodaje = signal<ProdajaModel[]>([]);
  primerci = signal<Primerak[]>([]);
  clanovi = signal<Clan[]>([]);

  prikaziFormu = false;
  greska = '';
  uspeh = '';

  forma = {
    primerakId: 0,
    clanId: 0,
    cena: 0,
    bezClana: false
  };

  constructor(
    private prodajaService: ProdajaService,
    private primerakService: PrimerakService,
    private clanService: ClanService,
    public authService: AuthService
  ) {}

  ngOnInit():void{
    this.ucitajProdaje();
    this.ucitajPrimerke();
    this.ucitajClanove();
  }

  ucitajProdaje(): void {
    this.prodajaService.getAll().subscribe({
      next: (data) => this.prodaje.set(data),
      error: () => this.greska = 'Greška pri učitavanju prodaja.'
    });
  }

  ucitajPrimerke(): void {
    this.primerakService.getAll().subscribe({
      next: (data) => {
        this.primerci.set(data.filter(p => p.status === 'DOSTUPAN'));
      }
    });
  }

  ucitajClanove(): void {
    this.clanService.getAll().subscribe({
      next: (data) => this.clanovi.set(data.filter(c => c.aktivan))
    });
  }

  otvoriFormu(): void {
    this.forma = { primerakId: 0, clanId: 0, cena: 0, bezClana: false };
    this.prikaziFormu = true;
    this.greska = '';
    this.uspeh = '';
  }

  onPrimerakChange():void{
    const primerak= this.primerci().find(p=>p.id === this.forma.primerakId);
    if(primerak && primerak.cenaProdaje){
      this.forma.cena= primerak.cenaProdaje;
    }
  }

  prodaj():void{
    if(!this.forma.primerakId || !this.forma.cena){
      this.greska = 'Molimo popunite sve obavezne podatke.';
      return;
    }
    if(!this.forma.bezClana && !this.forma.clanId){
      this.greska = 'Molimo odaberite člana ili označite "Bez člana".';
      return;
    }
    
    const payload: any = {
      primerak: { id: this.forma.primerakId },
      korisnik: { id: this.authService.getKorisnikId() },
      cena: this.forma.cena
    };

    if(!this.forma.bezClana && this.forma.clanId){
      payload.clan = { id: this.forma.clanId };
    }

    this.prodajaService.prodaj(payload).subscribe({
      next: () => {
        this.prikaziFormu = false;
        this.uspeh = 'Prodaja uspešno obavljena.';
       this.ucitajProdaje();
        this.ucitajPrimerke();
      },
      error: (err) => {this.greska =this.greska=err.error ||'Greška pri kreiranju prodaje.';
      }
    });
  }
  otkaziFormu():void{
    this.prikaziFormu=false;
    this.greska='';
  }
}