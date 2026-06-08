import { Clan } from "./clan";
import { Korisnik } from "./korisnik";
import { Primerak } from "./primerak";


export interface Iznajmljivanje {
  id: number;
  primerak: Primerak;
  clan: Clan;
  korisnik: Korisnik;
  datumIznajmljivanja: string;
  rokVracnja: string;
  datumVracanja?: string;
  status: string;
}