package rs.ac.singidunum.novisad.videoteka_projekat_isa.dto;

import java.util.ArrayList;
import java.util.List;

public class ZanrDTO {

    private Long id;
    private String naziv;
    private String opis;
    private List<FilmDTO> filmovi = new ArrayList<>();

    public ZanrDTO() {}

    public ZanrDTO(Long id, String naziv, String opis) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
    }

    public ZanrDTO(Long id, String naziv, String opis, List<FilmDTO> filmovi) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.filmovi = filmovi;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }

    public List<FilmDTO> getFilmovi() { return filmovi; }
    public void setFilmovi(List<FilmDTO> filmovi) { this.filmovi = filmovi; }

}
