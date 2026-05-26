package rs.ac.singidunum.novisad.videoteka_projekat_isa.dto;

import java.time.LocalDate;

public class ProdajaDTO {

    private Long id;
    private LocalDate datumProdaje;
    private Double cena;
    private PrimerakDTO primerak;
    private ClanDTO clan;
    private KorisnikDTO korisnik;

    public ProdajaDTO() {}

    public ProdajaDTO(Long id, LocalDate datumProdaje, Double cena) {
        this.id = id;
        this.datumProdaje = datumProdaje;
        this.cena = cena;
    }

    public ProdajaDTO(Long id, LocalDate datumProdaje, Double cena, PrimerakDTO primerak, ClanDTO clan, KorisnikDTO korisnik) {
        this.id = id;
        this.datumProdaje = datumProdaje;
        this.cena = cena;
        this.primerak = primerak;
        this.clan = clan;
        this.korisnik = korisnik;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDatumProdaje() { return datumProdaje; }
    public void setDatumProdaje(LocalDate datumProdaje) { this.datumProdaje = datumProdaje; }

    public Double getCena() { return cena; }
    public void setCena(Double cena) { this.cena = cena; }

    public PrimerakDTO getPrimerak() { return primerak; }
    public void setPrimerak(PrimerakDTO primerak) { this.primerak = primerak; }

    public ClanDTO getClan() { return clan; }
    public void setClan(ClanDTO clan) { this.clan = clan; }

    public KorisnikDTO getKorisnik() { return korisnik; }
    public void setKorisnik(KorisnikDTO korisnik) { this.korisnik = korisnik; }

}
