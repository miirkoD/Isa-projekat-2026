package rs.ac.singidunum.novisad.videoteka_projekat_isa.repository;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Prodaja;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdajaRepository extends JpaRepository<Prodaja, Long> {
	boolean existsByPrimerakId(Long primerakId);
    List<Prodaja> findByClanId(Long clanId);
}
