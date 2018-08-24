package rs.uns.ac.ftn.Diplomski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.Diplomski.model.Wine;
import rs.uns.ac.ftn.Diplomski.repository.WineRepository;
import rs.uns.ac.ftn.Diplomski.security.JWTUtils;

import java.util.List;

@Service
public class WineServiceImpl implements WineService {

    @Autowired
    private WineRepository wineRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    @Transactional(readOnly = true)
    public List<Wine> findAll() {
        return this.wineRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Wine findOne(Long id) {
        Wine wine = this.wineRepository.getOne(id);
        return wine;
    }

    @Override
    @Transactional(readOnly = false)
    public Wine save(Wine wine) {
        return this.wineRepository.save(wine);
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(Wine wine) {
        wine.setDeleted(true);
        this.wineRepository.save(wine);
    }

    @Override
    @Transactional(readOnly = true)
    public Wine findByWineName(String wineName){
        return this.wineRepository.findByWineName(wineName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wine> findBySubclassOfWine(Long id){
        return this.wineRepository.findBySubclassOfWine(id);
    }
}
