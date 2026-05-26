import { Film } from './film';

export interface Primerak {
  id: number;
  film?: Film;
  polovan: boolean;
  status: string;
  cenaIznajmljivanja?: number;
  cenaProdaje?: number;
}