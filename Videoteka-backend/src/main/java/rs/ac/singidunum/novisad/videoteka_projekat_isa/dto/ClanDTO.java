package rs.ac.singidunum.novisad.videoteka_projekat_isa.dto;

import java.util.ArrayList;
import java.util.List;

public class ClanDTO {

    private Long id;
    private String ime;
    private String prezime;
    private String brojClanskeKarte;
    private String email;
    private String telefon;
    private Boolean aktivan;
    private List<IznajmljivanjeDTO> iznajmljivanja = new ArrayList<>();
    private List<ProdajaDTO> prodaja = new ArrayList<>();

    public ClanDTO() {}

    public ClanDTO(Long id, String ime, String prezime, String brojClanskeKarte, String email, String telefon, Boolean aktivan) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.brojClanskeKarte = brojClanskeKarte;
        this.email = email;
        this.telefon = telefon;
        this.aktivan = aktivan;
    }

    public ClanDTO(Long id, String ime, String prezime, String brojClanskeKarte, String email, String telefon, Boolean aktivan, List<IznajmljivanjeDTO> iznajmljivanja, List<ProdajaDTO> prodaja) {
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }

    public String getBrojClanskeKarte() { return brojClanskeKarte; }
    public void setBrojClanskeKarte(String brojClanskeKarte) { this.brojClanskeKarte = brojClanskeKarte; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public Boolean getAktivan() { return aktivan; }
    public void setAktivan(Boolean aktivan) { this.aktivan = aktivan; }

    public List<IznajmljivanjeDTO> getIznajmljivanja() { return iznajmljivanja; }
    public void setIznajmljivanja(List<IznajmljivanjeDTO> iznajmljivanja) { this.iznajmljivanja = iznajmljivanja; }

    public List<ProdajaDTO> getProdaja() { return prodaja; }
    public void setProdaja(List<ProdajaDTO> prodaja) { this.prodaja = prodaja; }

}
