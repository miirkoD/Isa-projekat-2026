package rs.ac.singidunum.novisad.videoteka_projekat_isa.dto;

public class LoginResponse {
	private String token;
    private String rola;
    private Long id;
    
	public LoginResponse(String token, String rola,Long id) {
		super();
		this.token = token;
		this.rola = rola;
		this.id=id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public String getRola() {
		return rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}
    
}
