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
public class Iznajmljivanje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
	private Primerak primerak;
	
	@ManyToOne
    @JoinColumn(name = "clan_id", nullable = false)
	private Clan clan;
	
	@ManyToOne()
	@JoinColumn(name = "korisnik_id", nullable = false)
	private Korisnik korisnik;
	
	@Column(nullable = false)
	private LocalDate datumIznajmljivanja;
	
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false)
	private LocalDate rokVracnja;
	
	@Column()
	private LocalDate datumVracanja;

	public Iznajmljivanje() {
		super();
	}

	public Iznajmljivanje(Long id, Primerak primerak, Clan clan, Korisnik korisnik, LocalDate datumIznajmljivanja,
			String status, LocalDate rokVracnja, LocalDate datumVracanja) {
		super();
		this.id = id;
		this.primerak = primerak;
		this.clan = clan;
		this.korisnik = korisnik;
		this.datumIznajmljivanja = datumIznajmljivanja;
		this.status = status;
		this.rokVracnja = rokVracnja;
		this.datumVracanja = datumVracanja;
	}

	
	
	public Iznajmljivanje(Long id, LocalDate datumIznajmljivanja, String status,
			LocalDate rokVracnja, LocalDate datumVracanja) {
		super();
		this.id = id;
		this.datumIznajmljivanja = datumIznajmljivanja;
		this.status = status;
		this.rokVracnja = rokVracnja;
		this.datumVracanja = datumVracanja;
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

	public LocalDate getDatumIznajmljivanja() {
		return datumIznajmljivanja;
	}

	public void setDatumIznajmljivanja(LocalDate datumIznajmljivanja) {
		this.datumIznajmljivanja = datumIznajmljivanja;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getRokVracnja() {
		return rokVracnja;
	}

	public void setRokVracnja(LocalDate rokVracnja) {
		this.rokVracnja = rokVracnja;
	}

	public LocalDate getDatumVracanja() {
		return datumVracanja;
	}

	public void setDatumVracanja(LocalDate datumVracanja) {
		this.datumVracanja = datumVracanja;
	}
	
	
}
