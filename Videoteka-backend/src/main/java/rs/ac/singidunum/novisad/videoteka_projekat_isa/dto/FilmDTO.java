package rs.ac.singidunum.novisad.videoteka_projekat_isa.dto;

import java.util.ArrayList;
import java.util.List;

public class FilmDTO {

    private Long id;
    private String naslov;
    private Integer godina;
    private Integer trajanjeMin;
    private String reziser;
    private ZanrDTO zanr;
    private List<PrimerakDTO> primerci = new ArrayList<>();

    public FilmDTO() {}

    public FilmDTO(Long id, String naslov, Integer godina, Integer trajanjeMin, String reziser) {
        this.id = id;
        this.naslov = naslov;
        this.godina = godina;
        this.trajanjeMin = trajanjeMin;
        this.reziser = reziser;
    }

    public FilmDTO(Long id, String naslov, Integer godina, Integer trajanjeMin, String reziser, ZanrDTO zanr) {
        this.id = id;
        this.naslov = naslov;
        this.godina = godina;
        this.trajanjeMin = trajanjeMin;
        this.reziser = reziser;
        this.zanr = zanr;
    }

    public FilmDTO(Long id, String naslov, Integer godina, Integer trajanjeMin, String reziser, ZanrDTO zanr, List<PrimerakDTO> primerci) {
        this.id = id;
        this.naslov = naslov;
        this.godina = godina;
        this.trajanjeMin = trajanjeMin;
        this.reziser = reziser;
        this.zanr = zanr;
        this.primerci = primerci;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaslov() { return naslov; }
    public void setNaslov(String naslov) { this.naslov = naslov; }

    public Integer getGodina() { return godina; }
    public void setGodina(Integer godina) { this.godina = godina; }

    public Integer getTrajanjeMin() { return trajanjeMin; }
    public void setTrajanjeMin(Integer trajanjeMin) { this.trajanjeMin = trajanjeMin; }

    public String getReziser() { return reziser; }
    public void setReziser(String reziser) { this.reziser = reziser; }

    public ZanrDTO getZanr() { return zanr; }
    public void setZanr(ZanrDTO zanr) { this.zanr = zanr; }

    public List<PrimerakDTO> getPrimerci() { return primerci; }
    public void setPrimerci(List<PrimerakDTO> primerci) { this.primerci = primerci; }

}
