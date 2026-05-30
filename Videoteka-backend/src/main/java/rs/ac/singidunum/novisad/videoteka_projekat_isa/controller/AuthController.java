package rs.ac.singidunum.novisad.videoteka_projekat_isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.LoginRequest;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.LoginResponse;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Korisnik;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.repository.KorisnikRepository;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.security.CustomUserDetailsService;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.utils.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
			CustomUserDetailsService userDetailsService, KorisnikRepository korisnikRepository) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		this.korisnikRepository=korisnikRepository;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request){
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
					);
		}catch(BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pogresni kredencijali");
		}
			UserDetails userDetails=userDetailsService.loadUserByUsername(request.getUsername());
			String rola= userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
			
			String token= jwtUtil.generateToken(request.getUsername(), rola);
			
			Korisnik korisnik=korisnikRepository.findByUsername(request.getUsername()).orElseThrow();
			
			return ResponseEntity.ok(new LoginResponse(token,rola,korisnik.getId()));
	}
}
