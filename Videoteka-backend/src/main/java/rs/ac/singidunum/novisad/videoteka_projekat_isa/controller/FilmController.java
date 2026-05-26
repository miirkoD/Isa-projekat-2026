package rs.ac.singidunum.novisad.videoteka_projekat_isa.controller;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.FilmDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Film;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.service.FilmService;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.ZanrDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Zanr;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.service.ZanrService;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.PrimerakDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Primerak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/filmovi")
@CrossOrigin(origins = "http://localhost:4200")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private ZanrService zanrService;

    private FilmDTO buildDTO(Film e) {
        ZanrDTO zanrDTO = new ZanrDTO(
            e.getZanr().getId(),
            e.getZanr().getNaziv(),
            e.getZanr().getOpis()
        );
        List<PrimerakDTO> primerciDTOList = new ArrayList<>();
        for (Primerak item : e.getPrimerci()) {
            primerciDTOList.add(new PrimerakDTO(
                item.getId(),
                item.getPolovan(),
                item.getStatus(),
                item.getCenaIznajmljivanja(),
                item.getCenaProdaje()
            ));
        }
        return new FilmDTO(
            e.getId(),
            e.getNaslov(),
            e.getGodina(),
            e.getTrajanjeMin(),
            e.getReziser(),
            zanrDTO,
            primerciDTOList
        );
    }

    @RequestMapping(path = "", method = org.springframework.web.bind.annotation.RequestMethod.GET)
    public ArrayList<FilmDTO> findAll() {
        ArrayList<FilmDTO> list = new ArrayList<>();
        for (Film e : filmService.findAll())
            list.add(buildDTO(e));
        return list;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<FilmDTO> findById(@PathVariable("id") Long id) {
        Optional<Film> opt = filmService.findById(id);
        if (opt.isPresent())
            return new ResponseEntity<>(buildDTO(opt.get()), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<FilmDTO> deleteById(@PathVariable("id") Long id) {
        Optional<Film> opt = filmService.findById(id);
        if (opt.isPresent()) {
            FilmDTO dto = buildDTO(opt.get());
            filmService.deleteById(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "")
    public ResponseEntity<FilmDTO> create(@RequestBody FilmDTO dto) {
        if (dto.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<Zanr> zanrOpt = zanrService.findById(dto.getZanr().getId());
        if (!zanrOpt.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Film entity = new Film(null, dto.getNaslov(), dto.getGodina(), dto.getTrajanjeMin(), dto.getReziser(), zanrOpt.get());
        Film saved = filmService.save(entity);
        return new ResponseEntity<>(buildDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<FilmDTO> update(@PathVariable("id") Long id, @RequestBody FilmDTO dto) {
        Optional<Film> opt = filmService.findById(id);
        if (!opt.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Film entity = opt.get();
        entity.setNaslov(dto.getNaslov());
        entity.setGodina(dto.getGodina());
        entity.setTrajanjeMin(dto.getTrajanjeMin());
        entity.setReziser(dto.getReziser());
        Optional<Zanr> zanrOpt = zanrService.findById(dto.getZanr().getId());
        if (!zanrOpt.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        entity.setZanr(zanrOpt.get());
        Film saved = filmService.save(entity);
        return new ResponseEntity<>(buildDTO(saved), HttpStatus.OK);
    }
    
    @GetMapping("/pretraga")
    public List<FilmDTO> pretrazi(@RequestParam(required=false) String naslov, @RequestParam(required = false) String reziser,
            @RequestParam(required = false) Long zanrId){
    	List<Film> rezultati;
    	
    	if (naslov != null) {
            rezultati = filmService.findByNaslov(naslov);
        } else if (reziser != null) {
            rezultati = filmService.findByReziser(reziser);
        } else if (zanrId != null) {
            rezultati = filmService.findByZanr(zanrId);
        } else {
            rezultati = (List<Film>) filmService.findAll();
        }

    	List<FilmDTO> list = new ArrayList<>();
        for (Film e : rezultati)
            list.add(buildDTO(e));
        return list;
    }

}
