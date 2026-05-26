package rs.ac.singidunum.novisad.videoteka_projekat_isa.repository;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Korisnik;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	Optional<Korisnik> findByUsername(String username);
	List<Korisnik> findByRola(String rola);
}
