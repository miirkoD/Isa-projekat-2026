package rs.ac.singidunum.novisad.videoteka_projekat_isa.controller;

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
@RequestMapping(path = "/api/korisnici")
@CrossOrigin(origins = "http://localhost:4200")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    private KorisnikDTO buildDTO(Korisnik e) {
        return new KorisnikDTO(
            e.getId(),
            e.getIme(),
            e.getPrezime(),
            e.getUsername(),
            e.getPassword(),
            e.getRola(),
            e.getAktivan()
        );
    }

    @RequestMapping(path = "", method = org.springframework.web.bind.annotation.RequestMethod.GET)
    public ArrayList<KorisnikDTO> findAll() {
        ArrayList<KorisnikDTO> list = new ArrayList<>();
        for (Korisnik e : korisnikService.findAll())
            list.add(buildDTO(e));
        return list;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<KorisnikDTO> findById(@PathVariable("id") Long id) {
        Optional<Korisnik> opt = korisnikService.findById(id);
        if (opt.isPresent())
            return new ResponseEntity<>(buildDTO(opt.get()), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<KorisnikDTO> deleteById(@PathVariable("id") Long id) {
        Optional<Korisnik> opt = korisnikService.findById(id);
        if (opt.isPresent()) {
            KorisnikDTO dto = buildDTO(opt.get());
            korisnikService.deleteById(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "")
    public ResponseEntity<KorisnikDTO> create(@RequestBody KorisnikDTO dto) {
        if (dto.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Korisnik entity = new Korisnik(null, dto.getIme(), dto.getPrezime(), dto.getUsername(), dto.getPassword(), dto.getRola(), dto.getAktivan());
        Korisnik saved = korisnikService.save(entity);
        return new ResponseEntity<>(buildDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<KorisnikDTO> update(@PathVariable("id") Long id, @RequestBody KorisnikDTO dto) {
        Optional<Korisnik> opt = korisnikService.findById(id);
        if (!opt.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (dto.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Korisnik entity = opt.get();
        entity.setIme(dto.getIme());
        entity.setPrezime(dto.getPrezime());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRola(dto.getRola());
        entity.setAktivan(dto.getAktivan());
        Korisnik saved = korisnikService.save(entity);
        return new ResponseEntity<>(buildDTO(saved), HttpStatus.OK);
    }

}
