package rs.ac.singidunum.novisad.videoteka_projekat_isa.dto;

public class KorisnikDTO {

    private Long id;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private String rola;
    private Boolean aktivan;

    public KorisnikDTO() {}

    public KorisnikDTO(Long id, String ime, String prezime, String username, String password, String rola, Boolean aktivan) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.rola = rola;
        this.aktivan = aktivan;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRola() { return rola; }
    public void setRola(String rola) { this.rola = rola; }

    public Boolean getAktivan() { return aktivan; }
    public void setAktivan(Boolean aktivan) { this.aktivan = aktivan; }

}
