package rs.ac.singidunum.novisad.videoteka_projekat_isa.service;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Film;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public Iterable<Film> findAll() {
        return filmRepository.findAll();
    }

    public Optional<Film> findById(Long id) {
        return filmRepository.findById(id);
    }

    public Film save(Film entity) {
        return filmRepository.save(entity);
    }

    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }
    
    public List<Film> findByNaslov(String naslov){
    	return filmRepository.findByNaslovContainingIgnoreCase(naslov);
    }

    public List<Film> findByReziser(String reziser){
    	return filmRepository.findByReziserContainingIgnoreCase(reziser);
    }
    
    public List<Film> findByZanr(Long zanrId){
    	return filmRepository.findByZanrId(zanrId);
    }
}
