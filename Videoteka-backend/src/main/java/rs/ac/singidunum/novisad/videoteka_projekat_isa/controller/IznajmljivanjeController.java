package rs.ac.singidunum.novisad.videoteka_projekat_isa.controller;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.IznajmljivanjeDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Iznajmljivanje;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.service.IznajmljivanjeService;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.PrimerakDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Primerak;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.service.PrimerakService;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.ClanDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Clan;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.service.ClanService;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.KorisnikDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Korisnik;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/iznajmljivanja")
@CrossOrigin(origins = "http://localhost:4200")
public class IznajmljivanjeController {

    @Autowired
    private IznajmljivanjeService iznajmljivanjeService;

    @Autowired
    private PrimerakService primerakService;

    @Autowired
    private ClanService clanService;

    @Autowired
    private KorisnikService korisnikService;

    private IznajmljivanjeDTO buildDTO(Iznajmljivanje e) {
        PrimerakDTO primerakDTO = new PrimerakDTO(
            e.getPrimerak().getId(),
            e.getPrimerak().getPolovan(),
            e.getPrimerak().getStatus(),
            e.getPrimerak().getCenaIznajmljivanja(),
            e.getPrimerak().getCenaProdaje()
        );
        ClanDTO clanDTO = new ClanDTO(
            e.getClan().getId(),
            e.getClan().getIme(),
            e.getClan().getPrezime(),
            e.getClan().getBrojClanskeKarte(),
            e.getClan().getEmail(),
            e.getClan().getTelefon(),
            e.getClan().getAktivan()
        );
        KorisnikDTO korisnikDTO = new KorisnikDTO(
            e.getKorisnik().getId(),
            e.getKorisnik().getIme(),
            e.getKorisnik().getPrezime(),
            e.getKorisnik().getUsername(),
            e.getKorisnik().getPassword(),
            e.getKorisnik().getRola(),
            e.getKorisnik().getAktivan()
        );
        return new IznajmljivanjeDTO(
            e.getId(),
            e.getDatumIznajmljivanja(),
            e.getStatus(),
            e.getRokVracnja(),
            e.getDatumVracanja(),
            primerakDTO,
            clanDTO,
            korisnikDTO
        );
    }

    @RequestMapping(path = "", method = org.springframework.web.bind.annotation.RequestMethod.GET)
    public ArrayList<IznajmljivanjeDTO> findAll() {
        ArrayList<IznajmljivanjeDTO> list = new ArrayList<>();
        for (Iznajmljivanje e : iznajmljivanjeService.findAll())
            list.add(buildDTO(e));
        return list;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<IznajmljivanjeDTO> findById(@PathVariable("id") Long id) {
        Optional<Iznajmljivanje> opt = iznajmljivanjeService.findById(id);
        if (opt.isPresent())
            return new ResponseEntity<>(buildDTO(opt.get()), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<IznajmljivanjeDTO> deleteById(@PathVariable("id") Long id) {
        Optional<Iznajmljivanje> opt = iznajmljivanjeService.findById(id);
        if (opt.isPresent()) {
            IznajmljivanjeDTO dto = buildDTO(opt.get());
            iznajmljivanjeService.deleteById(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<IznajmljivanjeDTO> update(@PathVariable("id") Long id, @RequestBody IznajmljivanjeDTO dto) {
        Optional<Iznajmljivanje> opt = iznajmljivanjeService.findById(id);
        if (!opt.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (dto.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Iznajmljivanje entity = opt.get();
        entity.setDatumIznajmljivanja(dto.getDatumIznajmljivanja());
        entity.setStatus(dto.getStatus());
        entity.setRokVracnja(dto.getRokVracnja());
        entity.setDatumVracanja(dto.getDatumVracanja());
        Optional<Primerak> primerakOpt = primerakService.findById(dto.getPrimerak().getId());
        if (!primerakOpt.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        entity.setPrimerak(primerakOpt.get());
        Optional<Clan> clanOpt = clanService.findById(dto.getClan().getId());
        if (!clanOpt.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        entity.setClan(clanOpt.get());
        Optional<Korisnik> korisnikOpt = korisnikService.findById(dto.getKorisnik().getId());
        if (!korisnikOpt.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        entity.setKorisnik(korisnikOpt.get());
        Iznajmljivanje saved = iznajmljivanjeService.save(entity);
        return new ResponseEntity<>(buildDTO(saved), HttpStatus.OK);
    }
    
    @PostMapping(path="/iznajmi")
    public ResponseEntity<?> iznajmi(@RequestBody IznajmljivanjeDTO dto){
    	Optional<Primerak> primerakOpt=primerakService.findById(dto.getPrimerak().getId());
    	if(!primerakOpt.isPresent())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	
    	Optional<Clan> clanOpt=clanService.findById(dto.getClan().getId());
    	if(!clanOpt.isPresent())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	
    	Optional<Korisnik>korisnikOpt=korisnikService.findById(dto.getKorisnik().getId());
    	if(!korisnikOpt.isPresent())
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	try {
    		Iznajmljivanje entity=new Iznajmljivanje(null,primerakOpt.get(),clanOpt.get(),korisnikOpt.get(),
    				null, null, dto.getRokVracnja(), null);
    				
    		Iznajmljivanje saved= iznajmljivanjeService.iznajmi(entity);
    		return new ResponseEntity<>(buildDTO(saved),HttpStatus.CREATED);
    	}catch(RuntimeException e) {
    		return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    	}
    }
    
    @PutMapping("/vrati/{id}")
    public ResponseEntity<?> vrati(@PathVariable("id") Long id) {
        try {
            Iznajmljivanje saved = iznajmljivanjeService.vrati(id);
            return new ResponseEntity<>(buildDTO(saved), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/kasnjenja")
    public List<IznajmljivanjeDTO> getKasnjenja() {
        List<IznajmljivanjeDTO> list = new ArrayList<>();
        for (Iznajmljivanje e : iznajmljivanjeService.getKasnjenja())
            list.add(buildDTO(e));
        return list;
    }
    
    @GetMapping("/clan/{clanId}")
    public List<IznajmljivanjeDTO> getIstorijaClana(@PathVariable("clanId") Long clanId) {
        List<IznajmljivanjeDTO> list = new ArrayList<>();
        for (Iznajmljivanje e : iznajmljivanjeService.getIstorijaClana(clanId))
            list.add(buildDTO(e));
        return list;
    }

}
