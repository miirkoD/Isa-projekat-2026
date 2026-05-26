package rs.ac.singidunum.novisad.videoteka_projekat_isa.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Zanr {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String naziv;
	
	@Column(nullable=false)
	private String opis;
	
	@OneToMany(mappedBy = "zanr")
	private List<Film> filmovi=new ArrayList<Film>();
	
	public Zanr() {
		super();
	}

	public Zanr(Long id, String naziv, String opis, List<Film> filmovi) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.filmovi = filmovi;
	}

	public Zanr(Long id, String naziv, String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Film> getFilmovi() {
		return filmovi;
	}

	public void setFilmovi(List<Film> filmovi) {
		this.filmovi = filmovi;
	}
	
}
