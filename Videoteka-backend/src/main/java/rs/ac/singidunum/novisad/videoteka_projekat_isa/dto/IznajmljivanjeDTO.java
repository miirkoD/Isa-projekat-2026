package rs.ac.singidunum.novisad.videoteka_projekat_isa.dto;

import java.time.LocalDate;

public class IznajmljivanjeDTO {

    private Long id;
    private LocalDate datumIznajmljivanja;
    private String status;
    private LocalDate rokVracnja;
    private LocalDate datumVracanja;
    private PrimerakDTO primerak;
    private ClanDTO clan;
    private KorisnikDTO korisnik;

    public IznajmljivanjeDTO() {}

    public IznajmljivanjeDTO(Long id, LocalDate datumIznajmljivanja, String status, LocalDate rokVracnja, LocalDate datumVracanja) {
        this.id = id;
        this.datumIznajmljivanja = datumIznajmljivanja;
        this.status = status;
        this.rokVracnja = rokVracnja;
        this.datumVracanja = datumVracanja;
    }

    public IznajmljivanjeDTO(Long id, LocalDate datumIznajmljivanja, String status, LocalDate rokVracnja, LocalDate datumVracanja, PrimerakDTO primerak, ClanDTO clan, KorisnikDTO korisnik) {
        this.id = id;
        this.datumIznajmljivanja = datumIznajmljivanja;
        this.status = status;
        this.rokVracnja = rokVracnja;
        this.datumVracanja = datumVracanja;
        this.primerak = primerak;
        this.clan = clan;
        this.korisnik = korisnik;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDatumIznajmljivanja() { return datumIznajmljivanja; }
    public void setDatumIznajmljivanja(LocalDate datumIznajmljivanja) { this.datumIznajmljivanja = datumIznajmljivanja; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getRokVracnja() { return rokVracnja; }
    public void setRokVracnja(LocalDate rokVracnja) { this.rokVracnja = rokVracnja; }

    public LocalDate getDatumVracanja() { return datumVracanja; }
    public void setDatumVracanja(LocalDate datumVracanja) { this.datumVracanja = datumVracanja; }

    public PrimerakDTO getPrimerak() { return primerak; }
    public void setPrimerak(PrimerakDTO primerak) { this.primerak = primerak; }

    public ClanDTO getClan() { return clan; }
    public void setClan(ClanDTO clan) { this.clan = clan; }

    public KorisnikDTO getKorisnik() { return korisnik; }
    public void setKorisnik(KorisnikDTO korisnik) { this.korisnik = korisnik; }

}
