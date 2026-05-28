import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Clan } from '../../core/model/clan';
import { ClanService } from '../../core/services/clan-service';
import { AuthService } from '../../core/services/auth-service';

@Component({
  selector: 'app-clanovi',
  imports: [CommonModule,FormsModule],
  templateUrl: './clanovi.html',
  styleUrl: './clanovi.css',
})
export class Clanovi implements OnInit{
  clanovi = signal<Clan[]>([]);
  prikaziFormu = false;
  editMode = false;
  greska = '';

  forma: Partial<Clan> = {
    ime: '',
    prezime: '',
    brojClanskeKarte: '',
    email: '',
    telefon: '',
    aktivan: true
  };

  constructor(private clanService: ClanService,public authService:AuthService) {}

  ngOnInit(): void {
    this.clanService.getAll().subscribe({
      next: (data) => this.clanovi.set(data),
      error: (err) => this.greska = 'Neuspešno učitavanje članova: ' + err.message
    });
  }

  otvoriFormuZaDodavanje(): void {
    this.editMode = false;
    this.forma = {
      ime: '',
      prezime: '',
      brojClanskeKarte: '',
      email: '',
      telefon: '',
      aktivan: true
    };
    this.prikaziFormu = true;
    this.greska = '';
  }

  otvoriFormuZaIzmenu(clan: Clan): void {
    this.editMode = true;
    this.forma = {
      id: clan.id,
      ime: clan.ime,
      prezime: clan.prezime,
      brojClanskeKarte: clan.brojClanskeKarte,
      email: clan.email,
      telefon: clan.telefon,
      aktivan: clan.aktivan
    };
    this.prikaziFormu = true;
    this.greska = '';
  }

  sacuvaj():void{
    if(!this.forma.ime || !this.forma.prezime || !this.forma.brojClanskeKarte){
      this.greska = 'Ime, prezime i broj članske karte su obavezni.';
      return;
    }

    const payload: Partial<Clan> ={
      ime: this.forma.ime,
      prezime: this.forma.prezime,
      brojClanskeKarte: this.forma.brojClanskeKarte,
      email: this.forma.email,
      telefon: this.forma.telefon,
      aktivan: this.forma.aktivan
    };

    if(this.editMode && this.forma.id !== undefined){
      this.clanService.update(this.forma.id, payload).subscribe({
        next: () => {
          this.prikaziFormu = false;
          this.ngOnInit();
        },
        error: (err) => (this.greska = 'Neuspešno ažuriranje člana: ' + err.message)
      });
    } else {
      this.clanService.create(payload).subscribe({
        next: () => {
          this.prikaziFormu = false;
          this.ngOnInit();
        },
        error: (err) => (this.greska = 'Neuspešno kreiranje člana: ' + err.message)
      });
    }
  }

  deaktiviraj(clan: Clan):void{
    if(!confirm(`Da li ste sigurni da želite deaktivirati ${clan.ime} ${clan.prezime}?`)) return;

    this.clanService.update(clan.id,{...clan, aktivan: false}).subscribe({
      next: () => this.ngOnInit(),
      error: (err) => (this.greska = 'Neuspešno deaktiviranje člana: ' + err.message)
    });
  }

  aktiviraj(clan: Clan):void{
    if(!confirm(`Da li ste sigurni da želite aktivirati ${clan.ime} ${clan.prezime}?`)) return;

    this.clanService.update(clan.id,{...clan, aktivan: true}).subscribe({
      next: () => this.ngOnInit(),
      error: (err) => (this.greska = 'Neuspešno aktiviranje člana: ' + err.message)
    });
  }

  obrisi(id: number): void {
    if (!confirm('Da li ste sigurni da želite obrisati člana?')) return;

    this.clanService.delete(id).subscribe({
      next: () => this.ngOnInit(),
      error: (err) => (this.greska = 'Neuspešno brisanje člana: ' + err.message)
    });
  }
  
  otkaziFormu(): void {
    this.prikaziFormu = false;
    this.greska = '';
  }
}
