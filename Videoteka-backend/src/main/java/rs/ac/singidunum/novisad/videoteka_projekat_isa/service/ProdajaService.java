package rs.ac.singidunum.novisad.videoteka_projekat_isa.service;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Primerak;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Prodaja;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.repository.IznajmljivanjeRepository;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.repository.PrimerakRepository;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.repository.ProdajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProdajaService {

    @Autowired
    private ProdajaRepository prodajaRepository;
    
    @Autowired
    private PrimerakRepository primerakRepository;
    
    @Autowired
    private IznajmljivanjeRepository iznajmljivanjeRepository;
    
    public Iterable<Prodaja> findAll() {
        return prodajaRepository.findAll();
    }

    public Optional<Prodaja> findById(Long id) {
        return prodajaRepository.findById(id);
    }

    public void deleteById(Long id) {
        prodajaRepository.deleteById(id);
    }
    
    public Prodaja prodaj(Prodaja prodaja) {
    	Primerak primerak=prodaja.getPrimerak();
    	
    	if ("PRODAT".equals(primerak.getStatus())) {
            throw new RuntimeException("Primerak je vec prodat.");
        }
        if ("IZNAJMLJEN".equals(primerak.getStatus())) {
            throw new RuntimeException("Primerak je trenutno iznajmljen i ne moze se prodati.");
        }
        if (prodajaRepository.existsByPrimerakId(primerak.getId())) {
            throw new RuntimeException("Primerak je vec evidentiran kao prodat.");
        }
        primerak.setStatus("PRODAT");
        primerakRepository.save(primerak);
        
        prodaja.setDatumProdaje(LocalDate.now());
        return prodajaRepository.save(prodaja);
        
    }
    public List<Prodaja> getIstorijaClana(Long clanId) {
        return prodajaRepository.findByClanId(clanId);
    }

}
