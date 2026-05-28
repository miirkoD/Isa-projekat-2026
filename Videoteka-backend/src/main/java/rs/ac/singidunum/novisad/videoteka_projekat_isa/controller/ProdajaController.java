package rs.ac.singidunum.novisad.videoteka_projekat_isa.controller;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.ProdajaDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Prodaja;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.service.ProdajaService;
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
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/prodaje")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdajaController {

    @Autowired
    private ProdajaService prodajaService;

    @Autowired
    private PrimerakService primerakService;

    @Autowired
    private ClanService clanService;

    @Autowired
    private KorisnikService korisnikService;

    private ProdajaDTO buildDTO(Prodaja e) {
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
        return new ProdajaDTO(
            e.getId(),
            e.getDatumProdaje(),
            e.getCena(),
            primerakDTO,
            clanDTO,
            korisnikDTO
        );
    }

    @RequestMapping(path = "", method = org.springframework.web.bind.annotation.RequestMethod.GET)
    public ArrayList<ProdajaDTO> findAll() {
        ArrayList<ProdajaDTO> list = new ArrayList<>();
        for (Prodaja e : prodajaService.findAll())
            list.add(buildDTO(e));
        return list;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<ProdajaDTO> findById(@PathVariable("id") Long id) {
        Optional<Prodaja> opt = prodajaService.findById(id);
        if (opt.isPresent())
            return new ResponseEntity<>(buildDTO(opt.get()), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProdajaDTO> deleteById(@PathVariable("id") Long id) {
        Optional<Prodaja> opt = prodajaService.findById(id);
        if (opt.isPresent()) {
            ProdajaDTO dto = buildDTO(opt.get());
            prodajaService.deleteById(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "")
    public ResponseEntity<ProdajaDTO> create(@RequestBody ProdajaDTO dto) {
        if (dto.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<Primerak> primerakOpt = primerakService.findById(dto.getPrimerak().getId());
        if (!primerakOpt.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<Clan> clanOpt = clanService.findById(dto.getClan().getId());
        if (!clanOpt.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<Korisnik> korisnikOpt = korisnikService.findById(dto.getKorisnik().getId());
        if (!korisnikOpt.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Prodaja entity = new Prodaja(null,primerakOpt.get(), clanOpt.get(), korisnikOpt.get(), dto.getDatumProdaje(), dto.getCena());
        Prodaja saved = prodajaService.save(entity);
        return new ResponseEntity<>(buildDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProdajaDTO> update(@PathVariable("id") Long id, @RequestBody ProdajaDTO dto) {
        Optional<Prodaja> opt = prodajaService.findById(id);
        if (!opt.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (dto.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Prodaja entity = opt.get();
        entity.setDatumProdaje(dto.getDatumProdaje());
        entity.setCena(dto.getCena());
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
        Prodaja saved = prodajaService.save(entity);
        return new ResponseEntity<>(buildDTO(saved), HttpStatus.OK);
    }

}
