export interface Korisnik {
  id: number;
  ime: string;
  prezime: string;
  username: string;
  password?: string;
  rola: string;
  aktivan: boolean;
}