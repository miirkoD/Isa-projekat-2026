package rs.ac.singidunum.novisad.videoteka_projekat_isa.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Primerak {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "film_id", nullable = false)
	private Film film;
	
	@Column(nullable = false)
	private Boolean polovan;
	
	@Column(nullable = false)
	private String status;
	
	@Column()
	private Double cenaIznajmljivanja;
	
	@Column()
	private Double cenaProdaje;
	
	@OneToMany(mappedBy = "primerak")
	private List<Iznajmljivanje>iznajmljivanje=new ArrayList<Iznajmljivanje>();
	
	@OneToMany(mappedBy = "primerak")
	private List<Prodaja> prodaja=new ArrayList<Prodaja>();
	
	public Primerak() {
		super();
	}

	public Primerak(Long id, Film film, Boolean polovan, String status, Double cenaIznajmljivanja,
			Double cenaProdaje, List<Iznajmljivanje> iznajmljivanje,  List<Prodaja> prodaja) {
		super();
		this.id = id;
		this.film = film;
		this.polovan = polovan;
		this.status = status;
		this.cenaIznajmljivanja = cenaIznajmljivanja;
		this.cenaProdaje = cenaProdaje;
		this.iznajmljivanje = iznajmljivanje;
		this.prodaja = prodaja;
	}
	
	

	public Primerak(Long id, Boolean polovan, String status, Double cenaIznajmljivanja, Double cenaProdaje,Film film) {
		super();
		this.id = id;
		this.film = film;
		this.polovan = polovan;
		this.status = status;
		this.cenaIznajmljivanja = cenaIznajmljivanja;
		this.cenaProdaje = cenaProdaje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Boolean getPolovan() {
		return polovan;
	}

	public void setPolovan(Boolean polovan) {
		this.polovan = polovan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getCenaIznajmljivanja() {
		return cenaIznajmljivanja;
	}

	public void setCenaIznajmljivanja(Double cenaIznajmljivanja) {
		this.cenaIznajmljivanja = cenaIznajmljivanja;
	}

	public Double getCenaProdaje() {
		return cenaProdaje;
	}

	public void setCenaProdaje(Double cenaProdaje) {
		this.cenaProdaje = cenaProdaje;
	}

	public List<Iznajmljivanje> getIznajmljivanje() {
		return iznajmljivanje;
	}

	public void setIznajmljivanje(List<Iznajmljivanje> iznajmljivanje) {
		this.iznajmljivanje = iznajmljivanje;
	}

	public  List<Prodaja> getProdaja() {
		return prodaja;
	}

	public void setProdaja( List<Prodaja> prodaja) {
		this.prodaja = prodaja;
	}
	
	
}
