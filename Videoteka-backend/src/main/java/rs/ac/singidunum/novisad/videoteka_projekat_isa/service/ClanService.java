package rs.ac.singidunum.novisad.videoteka_projekat_isa.service;

import rs.ac.singidunum.novisad.videoteka_projekat_isa.model.Clan;
import rs.ac.singidunum.novisad.videoteka_projekat_isa.repository.ClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClanService {

    @Autowired
    private ClanRepository clanRepository;

    public Iterable<Clan> findAll() {
        return clanRepository.findAll();
    }

    public Optional<Clan> findById(Long id) {
        return clanRepository.findById(id);
    }

    public Clan save(Clan entity) {
        return clanRepository.save(entity);
    }

    public void deleteById(Long id) {
        clanRepository.deleteById(id);
    }

}
