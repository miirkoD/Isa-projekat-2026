package rs.ac.singidunum.novisad.videoteka_projekat_isa.service;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Korisnik;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    public Iterable<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }

    public Optional<Korisnik> findById(Long id) {
        return korisnikRepository.findById(id);
    }

    public Korisnik save(Korisnik entity) {
        return korisnikRepository.save(entity);
    }

    public void deleteById(Long id) {
        korisnikRepository.deleteById(id);
    }

}
