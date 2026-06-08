import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Zanr } from '../../core/model/zanr';
import { ZanrService } from '../../core/services/zanr-service';
import { AuthService } from '../../core/services/auth-service';

@Component({
  selector: 'app-zanrovi',
  imports: [CommonModule,FormsModule],
  templateUrl: './zanrovi.html',
  styleUrl: './zanrovi.css',
})
export class Zanrovi implements OnInit{
  zanrovi = signal<Zanr[]>([]);
  prikaziFormu = false;
  editMode = false;
  greska = '';
  uspeh = '';

  forma: Partial<Zanr> = {
    naziv: '',
    opis: ''
  };

  constructor(
    private zanrService: ZanrService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.ucitajZanrove();
  }

  ucitajZanrove(): void {
    this.zanrService.getAll().subscribe({
      next: (data) => this.zanrovi.set(data),
      error: () => this.greska = 'Greška pri učitavanju žanrova.'
    });
  }
  otvoriFormuZaDodavanje(): void {
    this.editMode = false;
    this.forma = { naziv: '', opis: '' };
    this.prikaziFormu = true;
    this.greska = '';
    this.uspeh = '';
  }

  otvoriFormuZaIzmenu(z: Zanr): void {
    this.editMode = true;
    this.forma = { id: z.id, naziv: z.naziv, opis: z.opis };
    this.prikaziFormu = true;
    this.greska = '';
    this.uspeh = '';
  }

  sacuvaj(): void {
    if (!this.forma.naziv) {
      this.greska = 'Naziv je obavezan.';
      return;
    }

    const payload: Partial<Zanr> = {
      naziv: this.forma.naziv,
      opis: this.forma.opis
    };

    if (this.editMode && this.forma.id !== undefined) {
      this.zanrService.update(this.forma.id, payload).subscribe({
        next: () => {
          this.prikaziFormu = false;
          this.uspeh = 'Žanr uspešno izmenjen.';
          this.ucitajZanrove();
        },
        error: () => this.greska = 'Greška pri izmeni žanra.'
      });
    } else {
      this.zanrService.create(payload).subscribe({
        next: () => {
          this.prikaziFormu = false;
          this.uspeh = 'Žanr uspešno dodat.';
          this.ucitajZanrove();
        },
        error: () => this.greska = 'Greška pri dodavanju žanra.'
      });
    }
  }

  obrisi(id: number): void {
    if (!confirm('Da li ste sigurni da želite da obrišete ovaj žanr?')) return;
    this.zanrService.delete(id).subscribe({
      next: () => {
        this.uspeh = 'Žanr obrisan.';
        this.ucitajZanrove();
      },
      error: () => this.greska = 'Greška pri brisanju. Žanr možda ima filmove.'
    });
  }

  otkaziFormu(): void {
    this.prikaziFormu = false;
    this.greska = '';
  }
}
