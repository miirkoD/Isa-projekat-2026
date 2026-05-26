package rs.ac.singidunum.novisad.videoteka_projekat_isa.controller;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.ZanrDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Zanr;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.service.ZanrService;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.FilmDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/zanrovi")
@CrossOrigin(origins = "http://localhost:4200")
public class ZanrController {

    @Autowired
    private ZanrService zanrService;

    private ZanrDTO buildDTO(Zanr e) {
        List<FilmDTO> filmoviDTOList = new ArrayList<>();
        for (Film item : e.getFilmovi()) {
            filmoviDTOList.add(new FilmDTO(
                item.getId(),
                item.getNaslov(),
                item.getGodina(),
                item.getTrajanjeMin(),
                item.getReziser()
            ));
        }
        return new ZanrDTO(
            e.getId(),
            e.getNaziv(),
            e.getOpis(),
            filmoviDTOList
        );
    }

    @RequestMapping(path = "", method = org.springframework.web.bind.annotation.RequestMethod.GET)
    public ArrayList<ZanrDTO> findAll() {
        ArrayList<ZanrDTO> list = new ArrayList<>();
        for (Zanr e : zanrService.findAll())
            list.add(buildDTO(e));
        return list;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<ZanrDTO> findById(@PathVariable("id") Long id) {
        Optional<Zanr> opt = zanrService.findById(id);
        if (opt.isPresent())
            return new ResponseEntity<>(buildDTO(opt.get()), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ZanrDTO> deleteById(@PathVariable("id") Long id) {
        Optional<Zanr> opt = zanrService.findById(id);
        if (opt.isPresent()) {
            ZanrDTO dto = buildDTO(opt.get());
            zanrService.deleteById(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "")
    public ResponseEntity<ZanrDTO> create(@RequestBody ZanrDTO dto) {
        if (dto.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Zanr entity = new Zanr(null, dto.getNaziv(), dto.getOpis());
        Zanr saved = zanrService.save(entity);
        return new ResponseEntity<>(buildDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ZanrDTO> update(@PathVariable("id") Long id, @RequestBody ZanrDTO dto) {
        Optional<Zanr> opt = zanrService.findById(id);
        if (!opt.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (dto.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Zanr entity = opt.get();
        entity.setNaziv(dto.getNaziv());
        entity.setOpis(dto.getOpis());
        Zanr saved = zanrService.save(entity);
        return new ResponseEntity<>(buildDTO(saved), HttpStatus.OK);
    }

}
