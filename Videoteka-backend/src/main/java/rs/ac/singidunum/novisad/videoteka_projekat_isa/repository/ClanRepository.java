package rs.ac.singidunum.novisad.videoteka_projekat_isa.repository;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Clan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClanRepository extends JpaRepository<Clan, Long> {

}
