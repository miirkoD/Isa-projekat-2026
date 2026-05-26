package rs.ac.singidunum.novisad.videoteka_projekat_isa.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prodaja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "primerak_id", nullable = false)
	private Primerak primerak;
	
	@ManyToOne()
	@JoinColumn(name = "clan_id")
	private Clan clan;
	
	@ManyToOne()
	@JoinColumn(name = "korisnik_id", nullable = false)
	private Korisnik korisnik;
	
	@Column()
	private LocalDate datumProdaje;
	
	@Column(nullable=false)
	private Double cena;

	public Prodaja() {
		super();
	}

	public Prodaja(Long id, Primerak primerak, Clan clan, Korisnik korisnik, LocalDate datumProdaje, Double cena) {
		super();
		this.id = id;
		this.primerak = primerak;
		this.clan = clan;
		this.korisnik = korisnik;
		this.datumProdaje = datumProdaje;
		this.cena = cena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Primerak getPrimerak() {
		return primerak;
	}

	public void setPrimerak(Primerak primerak) {
		this.primerak = primerak;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public LocalDate getDatumProdaje() {
		return datumProdaje;
	}

	public void setDatumProdaje(LocalDate datumProdaje) {
		this.datumProdaje = datumProdaje;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}
	
	
}
