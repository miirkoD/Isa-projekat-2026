import { Primerak } from './primerak';
import { Clan } from './clan';
import { Korisnik } from './korisnik';

export interface Prodaja {
  id: number;
  primerak: Primerak;
  clan?: Clan;
  korisnik: Korisnik;
  datumProdaje: string;
  cena: number;
}