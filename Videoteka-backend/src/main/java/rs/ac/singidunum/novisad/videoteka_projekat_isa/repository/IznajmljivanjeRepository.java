package rs.ac.singidunum.novisad.videoteka_projekat_isa.repository;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Iznajmljivanje;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IznajmljivanjeRepository extends JpaRepository<Iznajmljivanje, Long> {
	List<Iznajmljivanje> findByStatus(String status);
	List<Iznajmljivanje>findByClanId(Long clanId);
	boolean existsByPrimerakIdAndStatus(Long primerakId, String status);
}
