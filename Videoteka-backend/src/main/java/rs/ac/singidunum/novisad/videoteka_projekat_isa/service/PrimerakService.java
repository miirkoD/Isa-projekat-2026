package rs.ac.singidunum.novisad.videoteka_projekat_isa.service;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Primerak;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.repository.PrimerakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PrimerakService {

    @Autowired
    private PrimerakRepository primerakRepository;

    public Iterable<Primerak> findAll() {
        return primerakRepository.findAll();
    }

    public Optional<Primerak> findById(Long id) {
        return primerakRepository.findById(id);
    }

    public Primerak save(Primerak entity) {
        return primerakRepository.save(entity);
    }

    public void deleteById(Long id) {
        primerakRepository.deleteById(id);
    }

}
