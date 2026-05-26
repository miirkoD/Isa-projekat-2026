package rs.ac.singidunum.novisad.videoteka_projekat_isa.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column()
	private String naslov;
	@Column()
	private Integer godina;
	@Column()
	private Integer trajanjeMin;
	@Column()
	private String reziser;
	
	@ManyToOne
	private Zanr zanr;
	
	@OneToMany(mappedBy = "film")
	private List<Primerak> primerci=new ArrayList<Primerak>();

	public Film() {
		super();
	}

	public Film(Long id, String naslov, Integer godina, Integer trajanjeMin, String reziser, Zanr zanr,
			List<Primerak> primerci) {
		super();
		this.id = id;
		this.naslov = naslov;
		this.godina = godina;
		this.trajanjeMin = trajanjeMin;
		this.reziser = reziser;
		this.zanr = zanr;
		this.primerci = primerci;
	}

	
	
	public Film(Long id, String naslov, Integer godina, Integer trajanjeMin, String reziser, Zanr zanr) {
		super();
		this.id = id;
		this.naslov = naslov;
		this.godina = godina;
		this.trajanjeMin = trajanjeMin;
		this.reziser = reziser;
		this.zanr = zanr;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public Integer getGodina() {
		return godina;
	}

	public void setGodina(Integer godina) {
		this.godina = godina;
	}

	public Integer getTrajanjeMin() {
		return trajanjeMin;
	}

	public void setTrajanjeMin(Integer trajanjeMin) {
		this.trajanjeMin = trajanjeMin;
	}

	public String getReziser() {
		return reziser;
	}

	public void setReziser(String reziser) {
		this.reziser = reziser;
	}

	public Zanr getZanr() {
		return zanr;
	}

	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}

	public List<Primerak> getPrimerci() {
		return primerci;
	}

	public void setPrimerci(List<Primerak> primerci) {
		this.primerci = primerci;
	}
	
	
}
