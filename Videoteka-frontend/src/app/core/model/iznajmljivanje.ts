import { Clan } from "./clan";
import { Korisnik } from "./korisnik";
import { Primerak } from "./primerak";


export interface Iznajmljivanje {
  id: number;
  primerak: Primerak;
  clan: Clan;
  korisnik: Korisnik;
  datumIznajmljivanja: string;
  rokVracanja: string;
  datumVracanja?: string;
  status: string;
}