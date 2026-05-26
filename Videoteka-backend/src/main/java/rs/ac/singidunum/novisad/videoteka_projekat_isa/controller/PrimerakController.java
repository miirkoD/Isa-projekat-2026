package rs.ac.singidunum.novisad.videoteka_projekat_isa.controller;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.PrimerakDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Primerak;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.service.PrimerakService;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.FilmDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Film;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.service.FilmService;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.IznajmljivanjeDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Iznajmljivanje;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.ProdajaDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Prodaja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/primerci")
@CrossOrigin(origins = "http://localhost:4200")
public class PrimerakController {

    @Autowired
    private PrimerakService primerakService;

    @Autowired
    private FilmService filmService;

    private PrimerakDTO buildDTO(Primerak e) {
        FilmDTO filmDTO = new FilmDTO(
            e.getFilm().getId(),
            e.getFilm().getNaslov(),
            e.getFilm().getGodina(),
            e.getFilm().getTrajanjeMin(),
            e.getFilm().getReziser()
        );
        List<IznajmljivanjeDTO> iznajmljivanjeDTOList = new ArrayList<>();
        for (Iznajmljivanje item : e.getIznajmljivanje()) {
            iznajmljivanjeDTOList.add(new IznajmljivanjeDTO(
                item.getId(),
                item.getDatumIznajmljivanja(),
                item.getStatus(),
                item.getRokVracnja(),
                item.getDatumVracanja()
            ));
        }
        List<ProdajaDTO> prodajaDTOList = new ArrayList<>();
        for (Prodaja item : e.getProdaja()) {
            prodajaDTOList.add(new ProdajaDTO(
                item.getId(),
                item.getDatumProdaje(),
                item.getCena()
            ));
        }
        return new PrimerakDTO(
            e.getId(),
            e.getPolovan(),
            e.getStatus(),
            e.getCenaIznajmljivanja(),
            e.getCenaProdaje(),
            filmDTO,
            iznajmljivanjeDTOList,
            prodajaDTOList
        );
    }

    @RequestMapping(path = "", method = org.springframework.web.bind.annotation.RequestMethod.GET)
    public ArrayList<PrimerakDTO> findAll() {
        ArrayList<PrimerakDTO> list = new ArrayList<>();
        for (Primerak e : primerakService.findAll())
            list.add(buildDTO(e));
        return list;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<PrimerakDTO> findById(@PathVariable("id") Long id) {
        Optional<Primerak> opt = primerakService.findById(id);
        if (opt.isPresent())
            return new ResponseEntity<>(buildDTO(opt.get()), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<PrimerakDTO> deleteById(@PathVariable("id") Long id) {
        Optional<Primerak> opt = primerakService.findById(id);
        if (opt.isPresent()) {
            PrimerakDTO dto = buildDTO(opt.get());
            primerakService.deleteById(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "")
    public ResponseEntity<PrimerakDTO> create(@RequestBody PrimerakDTO dto) {
        if (dto.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<Film> filmOpt = filmService.findById(dto.getFilm().getId());
        if (!filmOpt.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Primerak entity = new Primerak(null, dto.getPolovan(), dto.getStatus(), dto.getCenaIznajmljivanja(), dto.getCenaProdaje(), filmOpt.get());
        Primerak saved = primerakService.save(entity);
        return new ResponseEntity<>(buildDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PrimerakDTO> update(@PathVariable("id") Long id, @RequestBody PrimerakDTO dto) {
        Optional<Primerak> opt = primerakService.findById(id);
        if (!opt.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (dto.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Primerak entity = opt.get();
        entity.setPolovan(dto.getPolovan());
        entity.setStatus(dto.getStatus());
        entity.setCenaIznajmljivanja(dto.getCenaIznajmljivanja());
        entity.setCenaProdaje(dto.getCenaProdaje());
        Optional<Film> filmOpt = filmService.findById(dto.getFilm().getId());
        if (!filmOpt.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        entity.setFilm(filmOpt.get());
        Primerak saved = primerakService.save(entity);
        return new ResponseEntity<>(buildDTO(saved), HttpStatus.OK);
    }

}
