package rs.ac.singidunum.novisad.videoteka_projekat_isa.service;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Iznajmljivanje;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Primerak;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.repository.IznajmljivanjeRepository;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.repository.PrimerakRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IznajmljivanjeService {

    @Autowired
    private IznajmljivanjeRepository iznajmljivanjeRepository;
    
    @Autowired
    private PrimerakRepository primerakRepository;
    
    public Iterable<Iznajmljivanje> findAll() {
        return iznajmljivanjeRepository.findAll();
    }

    public Optional<Iznajmljivanje> findById(Long id) {
        return iznajmljivanjeRepository.findById(id);
    }
    
    public Iznajmljivanje save(Iznajmljivanje entity) {
        return iznajmljivanjeRepository.save(entity);
    }

    public void deleteById(Long id) {
        iznajmljivanjeRepository.deleteById(id);
    }
    
    public Iznajmljivanje iznajmi(Iznajmljivanje iznajmljivanje) {
    	Primerak primerak=iznajmljivanje.getPrimerak();
    	
    	if(!primerak.getPolovan()) {
    		throw new RuntimeException("Novi primerci se ne mogu iznajmljivati.");
    	}
    	if(!"DOSTUPAN".equals(primerak.getStatus())) {
    		throw new RuntimeException("Primerak nije dostupan za iznajmljivanje.");
    	}
    	primerak.setStatus("IZNAJMLJEN");
    	primerakRepository.save(primerak);
    	
    	iznajmljivanje.setDatumIznajmljivanja(LocalDate.now());
    	iznajmljivanje.setStatus("AKTIVNO");
    	return iznajmljivanjeRepository.save(iznajmljivanje);
    }
    
    public Iznajmljivanje vrati(Long iznajmljivanjeId) {
    	Iznajmljivanje iznajmljivanje=iznajmljivanjeRepository.findById(iznajmljivanjeId)
    			.orElseThrow(()->new RuntimeException("Iznajmljivanje nije pronadjeno."));
    	if(!"AKTIVNO".equals(iznajmljivanje.getStatus())&&!"KASNI".equals(iznajmljivanje.getStatus())) {
    		throw new RuntimeException("Iznajmljivanje nije aktivno");
    	}
    	
    	iznajmljivanje.setDatumVracanja(LocalDate.now());
    	iznajmljivanje.setStatus("VRACENO");
    	
    	Primerak primerak = iznajmljivanje.getPrimerak();
    	primerak.setStatus("DOSTUPAN");
    	primerakRepository.save(primerak);
    	
    	return iznajmljivanjeRepository.save(iznajmljivanje);
    }
    
    public List<Iznajmljivanje> getKasnjenja(){
    	List<Iznajmljivanje> aktivna=iznajmljivanjeRepository.findByStatus("AKTIVNO");
    	List<Iznajmljivanje> kasnjenja=new ArrayList<>();
    	LocalDate danas= LocalDate.now();
    	
    	for(Iznajmljivanje i: aktivna) {
    		if(i.getRokVracnja().isBefore(danas)) {
    			i.setStatus("KASNI");
    			iznajmljivanjeRepository.save(i);
    			kasnjenja.add(i);
    		}
    	}
    	return kasnjenja;
    }
    
    public List<Iznajmljivanje>getIstorijaClana(Long clanId){
    	return iznajmljivanjeRepository.findByClanId(clanId);
    }
    
}
