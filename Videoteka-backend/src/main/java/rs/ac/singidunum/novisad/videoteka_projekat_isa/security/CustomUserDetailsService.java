package rs.ac.singidunum.novisad.videoteka_projekat_isa.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Korisnik;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.repository.KorisnikRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private final KorisnikRepository korisnikRepositor;
	
	public CustomUserDetailsService(KorisnikRepository korisnikRepository) {
		this.korisnikRepositor=korisnikRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik korisnik=korisnikRepositor.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("Korisnik nije pronadjen "+username));
		if(!korisnik.getAktivan()) {
			throw new UsernameNotFoundException("Korisnik nije aktivan");
		}
		
		return User.builder().username(korisnik.getUsername()).password(korisnik.getPassword())
				.roles(korisnik.getRola()).build();
	}

}
