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
public class Clan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String ime;
	
	@Column(nullable=false)
	private String prezime;
	
	@Column(nullable=false)
	private String brojClanskeKarte;
	
	@Column()
	private String email;
	
	@Column()
	private String telefon;
	
	@Column(nullable=false)
	private Boolean aktivan=true;
	
	@OneToMany(mappedBy = "clan")
	private List<Iznajmljivanje> iznajmljivanja=new ArrayList<Iznajmljivanje>();
	
	@OneToMany(mappedBy = "clan")
	private List<Prodaja> prodaja=new ArrayList<Prodaja>();

	public Clan() {
		super();
	}

	public Clan(Long id, String ime, String prezime, String brojClanskeKarte, String email, String telefon,
			Boolean aktivan, List<Iznajmljivanje> iznajmljivanja, List<Prodaja> prodaja) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.brojClanskeKarte = brojClanskeKarte;
		this.email = email;
		this.telefon = telefon;
		this.aktivan = aktivan;
		this.iznajmljivanja = iznajmljivanja;
		this.prodaja = prodaja;
	}
	
	public Clan(Long id, String ime, String prezime, String brojClanskeKarte, String email, String telefon,
		Boolean aktivan) {
	super();
	this.id = id;
	this.ime = ime;
	this.prezime = prezime;
	this.brojClanskeKarte = brojClanskeKarte;
	this.email = email;
	this.telefon = telefon;
	this.aktivan = aktivan;
}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getBrojClanskeKarte() {
		return brojClanskeKarte;
	}

	public void setBrojClanskeKarte(String brojClanskeKarte) {
		this.brojClanskeKarte = brojClanskeKarte;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Boolean getAktivan() {
		return aktivan;
	}

	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}

	public List<Iznajmljivanje> getIznajmljivanja() {
		return iznajmljivanja;
	}

	public void setIznajmljivanja(List<Iznajmljivanje> iznajmljivanja) {
		this.iznajmljivanja = iznajmljivanja;
	}

	public List<Prodaja> getProdaja() {
		return prodaja;
	}

	public void setProdaja(List<Prodaja> prodaja) {
		this.prodaja = prodaja;
	}
	
	
}
