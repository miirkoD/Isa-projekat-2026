package rs.ac.singidunum.novisad.videoteka_projekat_isa.dto;

import java.util.ArrayList;
import java.util.List;

public class PrimerakDTO {

    private Long id;
    private Boolean polovan;
    private String status;
    private Double cenaIznajmljivanja;
    private Double cenaProdaje;
    private FilmDTO film;
    private List<IznajmljivanjeDTO> iznajmljivanje = new ArrayList<>();
    private List<ProdajaDTO> prodaja = new ArrayList<>();

    public PrimerakDTO() {}

    public PrimerakDTO(Long id, Boolean polovan, String status, Double cenaIznajmljivanja, Double cenaProdaje) {
        this.id = id;
        this.polovan = polovan;
        this.status = status;
        this.cenaIznajmljivanja = cenaIznajmljivanja;
        this.cenaProdaje = cenaProdaje;
    }

    public PrimerakDTO(Long id, Boolean polovan, String status, Double cenaIznajmljivanja, Double cenaProdaje, FilmDTO film) {
        this.id = id;
        this.polovan = polovan;
        this.status = status;
        this.cenaIznajmljivanja = cenaIznajmljivanja;
        this.cenaProdaje = cenaProdaje;
        this.film = film;
    }

    public PrimerakDTO(Long id, Boolean polovan, String status, Double cenaIznajmljivanja, Double cenaProdaje, FilmDTO film, List<IznajmljivanjeDTO> iznajmljivanje, List<ProdajaDTO> prodaja) {
        this.id = id;
        this.polovan = polovan;
        this.status = status;
        this.cenaIznajmljivanja = cenaIznajmljivanja;
        this.cenaProdaje = cenaProdaje;
        this.film = film;
        this.iznajmljivanje = iznajmljivanje;
        this.prodaja = prodaja;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getPolovan() { return polovan; }
    public void setPolovan(Boolean polovan) { this.polovan = polovan; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getCenaIznajmljivanja() { return cenaIznajmljivanja; }
    public void setCenaIznajmljivanja(Double cenaIznajmljivanja) { this.cenaIznajmljivanja = cenaIznajmljivanja; }

    public Double getCenaProdaje() { return cenaProdaje; }
    public void setCenaProdaje(Double cenaProdaje) { this.cenaProdaje = cenaProdaje; }

    public FilmDTO getFilm() { return film; }
    public void setFilm(FilmDTO film) { this.film = film; }

    public List<IznajmljivanjeDTO> getIznajmljivanje() { return iznajmljivanje; }
    public void setIznajmljivanje(List<IznajmljivanjeDTO> iznajmljivanje) { this.iznajmljivanje = iznajmljivanje; }

    public List<ProdajaDTO> getProdaja() { return prodaja; }
    public void setProdaja(List<ProdajaDTO> prodaja) { this.prodaja = prodaja; }

}
