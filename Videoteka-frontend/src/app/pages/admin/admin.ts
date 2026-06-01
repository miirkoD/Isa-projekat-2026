import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Korisnik } from '../../core/model/korisnik';
import { KorisnikService } from '../../core/services/korisnik-service';
import { AuthService } from '../../core/services/auth-service';

@Component({
  selector: 'app-admin',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.html',
  styleUrl: './admin.css',
})
export class Admin implements OnInit {

  korisnici= signal<Korisnik[]>([]);
  prikaziFormu = false;
  editMode = false;
  greska = '';
  uspeh = '';

  forma: Partial<Korisnik> = {
    ime: '',
    prezime: '',
    username: '',
    password: '',
    rola: 'ZAPOSLENI',
    aktivan: true
  };

  constructor(
    private korisnikService: KorisnikService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.ucitajKorisnike();
  }

  ucitajKorisnike(): void {
    this.korisnikService.getAll().subscribe({
      next: (data) => this.korisnici.set(data),
      error: () => this.greska = 'Greška pri učitavanju korisnika.'
    });
  }

  otvoriFormuZaDodavanje(): void {
    this.editMode = false;
    this.forma = {
      ime: '',
      prezime: '',
      username: '',
      password: '',
      rola: 'ZAPOSLENI',
      aktivan: true
    };
    this.prikaziFormu = true;
    this.greska = '';
    this.uspeh = '';
  }

  otvoriFormuZaIzmenu(k: Korisnik): void {
    this.editMode = true;
    this.forma = {
      id: k.id,
      ime: k.ime,
      prezime: k.prezime,
      username: k.username,
      password: k.password,
      rola: k.rola,
      aktivan: k.aktivan
    };
    this.prikaziFormu = true;
    this.greska = '';
    this.uspeh = '';
  }

  sacuvaj(): void{
    if(!this.forma.ime || !this.forma.prezime || !this.forma.username || !this.forma.password){
      this.greska = 'Sva polja su obavezna.';
      return;
    }
    const payload: Partial<Korisnik> = {
      ime: this.forma.ime,
      prezime: this.forma.prezime,
      username: this.forma.username,
      password: this.forma.password,
      rola: this.forma.rola,
      aktivan: this.forma.aktivan
    };
    if (this.editMode && this.forma.id !== undefined) {
      this.korisnikService.update(this.forma.id, payload).subscribe({
        next: () => {
          this.prikaziFormu = false;
          this.uspeh = 'Korisnik uspešno izmenjen.';
          this.ucitajKorisnike();
        },
        error: () => this.greska = 'Greška pri izmeni korisnika.'
      });
    } else {
      this.korisnikService.create(payload).subscribe({
        next: () => {
          this.prikaziFormu = false;
          this.uspeh = 'Korisnik uspešno kreiran.';
          this.ucitajKorisnike();
        },
        error: () => this.greska = 'Greška pri kreiranju korisnika.'
      });
    }
  }

  deaktiviraj(k: Korisnik): void {
    if (!confirm(`Deaktiviraj korisnika ${k.username}?`)) return;
    this.korisnikService.update(k.id, { ...k, aktivan: false }).subscribe({
      next: () => {
        this.uspeh = 'Korisnik deaktiviran.';
        this.ucitajKorisnike();
      },
      error: () => this.greska = 'Greška pri deaktivaciji.'
    });
  }

  obrisi(id: number): void {
    if (!confirm('Da li ste sigurni da želite da obrišete ovog korisnika?')) return;
    this.korisnikService.delete(id).subscribe({
      next: () => {
        this.uspeh = 'Korisnik obrisan.';
        this.ucitajKorisnike();
      },
      error: () => this.greska = 'Greška pri brisanju korisnika.'
    });
  }

  otkaziFormu(): void {
    this.prikaziFormu = false;
    this.greska = '';
  }
}
