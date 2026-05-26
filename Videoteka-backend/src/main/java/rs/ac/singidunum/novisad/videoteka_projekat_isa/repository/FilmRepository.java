package rs.ac.singidunum.novisad.videoteka_projekat_isa.repository;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Film;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
	public List<Film>findByZanrId(Long id);
	
	public List<Film>findByReziserContainingIgnoreCase(String reziser);
	
	public List<Film>findByNaslovContainingIgnoreCase(String naslov);
}
