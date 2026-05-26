package rs.ac.singidunum.novisad.videoteka_projekat_isa.service;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Prodaja;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.repository.ProdajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProdajaService {

    @Autowired
    private ProdajaRepository prodajaRepository;
    
    public Iterable<Prodaja> findAll() {
        return prodajaRepository.findAll();
    }

    public Optional<Prodaja> findById(Long id) {
        return prodajaRepository.findById(id);
    }

    public Prodaja save(Prodaja entity) {
        return prodajaRepository.save(entity);
    }

    public void deleteById(Long id) {
        prodajaRepository.deleteById(id);
    }

}
