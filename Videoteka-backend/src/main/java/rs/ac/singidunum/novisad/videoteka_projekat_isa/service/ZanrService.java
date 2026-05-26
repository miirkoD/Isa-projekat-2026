package rs.ac.singidunum.novisad.videoteka_projekat_isa.service;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Zanr;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.repository.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ZanrService {

    @Autowired
    private ZanrRepository zanrRepository;

    public Iterable<Zanr> findAll() {
        return zanrRepository.findAll();
    }

    public Optional<Zanr> findById(Long id) {
        return zanrRepository.findById(id);
    }

    public Zanr save(Zanr entity) {
        return zanrRepository.save(entity);
    }

    public void deleteById(Long id) {
        zanrRepository.deleteById(id);
    }

}
