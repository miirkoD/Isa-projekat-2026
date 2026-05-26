import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FilmService } from '../../core/services/film-service';
import { ZanrService } from '../../core/services/zanr-service';
import { Film } from '../../core/model/film';
import { Zanr } from '../../core/model/zanr';
import { AuthService } from '../../core/services/auth-service';

@Component({
  selector: 'app-filmovi',
  imports: [CommonModule, FormsModule],
  templateUrl: './filmovi.html',
  styleUrl: './filmovi.css',
})
export class Filmovi implements OnInit {
  filmovi= signal<Film[]>([]);
  zanrovi= signal<Zanr[]>([]);

  pretragaNaslov = '';
  pretragaReziser = '';
  pretragaZanr: number | undefined;

  prikaziFormu = false;
  editMode = false;

  forma: Partial<Film>  = {
    naslov: '',
    godina: undefined,
    trajanjeMin: undefined,
    reziser: '',
    zanr: { id: 0, naziv: '' },
  };

  greska = '';

  constructor(
    private filmService: FilmService,
    private zanrService: ZanrService,
    public authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.ucitajFilmove();
    this.ucitajZanrove();
  }

  ucitajFilmove(): void {
    this.filmService.getAll().subscribe({
      next: (data) => (this.filmovi.set(data)),
      error: (err) => (this.greska = 'Neuspešno učitavanje filmova'),
    });
  }

  ucitajZanrove(): void {
    this.zanrService.getAll().subscribe({
      next: (data) => (this.zanrovi.set(data)),
      error: (err) => (this.greska = 'Neuspešno učitavanje žanrova'),
    });
  }

  pretrazi(): void {
    const imaParametre =
      (this.pretragaNaslov && this.pretragaNaslov.trim() !== '') ||
      (this.pretragaReziser && this.pretragaReziser.trim() !== '') ||
      this.pretragaZanr;

    if (!imaParametre) {
      this.ucitajFilmove();
      return;
    }

    this.filmService
      .pretrazi(
        this.pretragaNaslov || undefined,
        this.pretragaReziser || undefined,
        this.pretragaZanr,
      )
      .subscribe({
        next: (data) => (this.filmovi.set(data)),
        error: () => (this.greska = 'Greška pri pretrazi.'),
      });
  }

  resetujPretragu(): void {
    this.pretragaNaslov = '';
    this.pretragaReziser = '';
    this.pretragaZanr = undefined;
    this.ucitajFilmove();
  }

  otvoriFormuZaDodavanje(): void {
    this.editMode = false;
    this.forma = {
      naslov: '',
      godina: undefined,
      trajanjeMin: undefined,
      reziser: '',
      zanr: { id: 0, naziv: '' },
    };
    this.prikaziFormu = true;
    this.greska = '';
  }

  otvoriFormuZaIzmenu(film: any): void {
    this.editMode = true;
    this.forma = {
      id: film.id,
      naslov: film.naslov,
      godina: film.godina,
      trajanjeMin: film.trajanjeMin,
      reziser: film.reziser,
      zanr: { id: film.zanr.id, naziv: film.zanr.naziv },
    };
    this.prikaziFormu = true;
    this.greska = '';
  }

  stanjeNaziv(polovan: boolean): string {
    return polovan ? 'Polovan' : 'Nov';
  }

  sacuvaj(): void {
    const zanrId = this.forma.zanr?.id;

    if (!this.forma.naslov || !zanrId) {
      this.greska = 'Naslov i zanr su obavezni';
      return;
    }

    const payload: Partial<Film> = {
      naslov: this.forma.naslov,
      godina: this.forma.godina,
      trajanjeMin: this.forma.trajanjeMin,
      reziser: this.forma.reziser,
      zanr: { id: zanrId, naziv:'' },
    };

    if (this.editMode && this.forma.id !==undefined) {
      this.filmService.update(this.forma.id, payload).subscribe({
        next: () => {
          this.prikaziFormu = false;
          this.ucitajFilmove();
        },
        error: () => (this.greska = 'Greska pri izmeni filma'),
      });
    } else {
      this.filmService.create(payload).subscribe({
        next: () => {
          this.prikaziFormu = false;
          this.ucitajFilmove();
        },
        error: () => (this.greska = 'Greska pri dodavanju filma'),
      });
    }
  }

  obrisi(id: number): void {
    if (!confirm('Da li ste sigurni da zelite da obrisete film?')) return;
    this.filmService.delete(id).subscribe({
      next: () => this.ucitajFilmove(),
      error: () => (this.greska = 'Greska pri brisanju filma'),
    });
  }

  otkaziFormu(): void {
    this.prikaziFormu = false;
    this.greska = '';
  }
}
