import { Primerak } from "./primerak";
import { Zanr } from "./zanr";

export interface Film {
  id: number;
  naslov: string;
  godina?: number;
  trajanjeMin?: number;
  reziser?: string;
  zanr: Zanr;
  primerci?: Primerak[];
}