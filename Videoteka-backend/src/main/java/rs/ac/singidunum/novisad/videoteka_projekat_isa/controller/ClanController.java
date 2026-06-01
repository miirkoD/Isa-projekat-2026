package rs.ac.singidunum.novisad.videoteka_projekat_isa.controller;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.dto.ClanDTO;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Clan;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.service.ClanService;
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
@RequestMapping(path = "/api/clanovi")
@CrossOrigin(origins = "http://localhost:4200")
public class ClanController {

    @Autowired
    private ClanService clanService;

    private ClanDTO buildDTO(Clan e) {
        List<IznajmljivanjeDTO> iznajmljivanjaDTOList = new ArrayList<>();
        for (Iznajmljivanje item : e.getIznajmljivanja()) {
            iznajmljivanjaDTOList.add(new IznajmljivanjeDTO(
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
        return new ClanDTO(
            e.getId(),
            e.getIme(),
            e.getPrezime(),
            e.getBrojClanskeKarte(),
            e.getEmail(),
            e.getTelefon(),
            e.getAktivan(),
            iznajmljivanjaDTOList,
            prodajaDTOList
        );
    }

    @RequestMapping(path = "", method = org.springframework.web.bind.annotation.RequestMethod.GET)
    public ArrayList<ClanDTO> findAll() {
        ArrayList<ClanDTO> list = new ArrayList<>();
        for (Clan e : clanService.findAll())
            list.add(buildDTO(e));
        return list;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<ClanDTO> findById(@PathVariable("id") Long id) {
        Optional<Clan> opt = clanService.findById(id);
        if (opt.isPresent())
            return new ResponseEntity<>(buildDTO(opt.get()), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ClanDTO> deleteById(@PathVariable("id") Long id) {
        Optional<Clan> opt = clanService.findById(id);
        if (opt.isPresent()) {
            ClanDTO dto = buildDTO(opt.get());
            clanService.deleteById(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "")
    public ResponseEntity<ClanDTO> create(@RequestBody ClanDTO dto) {
        if (dto.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Clan entity = new Clan(null, dto.getIme(), dto.getPrezime(), dto.getBrojClanskeKarte(), dto.getEmail(), dto.getTelefon(), dto.getAktivan());
        Clan saved = clanService.save(entity);
        return new ResponseEntity<>(buildDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClanDTO> update(@PathVariable("id") Long id, @RequestBody ClanDTO dto) {
        Optional<Clan> opt = clanService.findById(id);
        if (!opt.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        if (dto.getId() != null)
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Clan entity = opt.get();
        entity.setIme(dto.getIme());
        entity.setPrezime(dto.getPrezime());
        entity.setBrojClanskeKarte(dto.getBrojClanskeKarte());
        entity.setEmail(dto.getEmail());
        entity.setTelefon(dto.getTelefon());
        entity.setAktivan(dto.getAktivan());
        Clan saved = clanService.save(entity);
        return new ResponseEntity<>(buildDTO(saved), HttpStatus.OK);
    }

}
