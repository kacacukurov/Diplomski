package rs.uns.ac.ftn.Diplomski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.Diplomski.model.Winery;
import rs.uns.ac.ftn.Diplomski.repository.WineryRepository;
import rs.uns.ac.ftn.Diplomski.security.JWTUtils;

import java.util.List;

@Service
public class WineryServiceImpl implements WineryService {

    @Autowired
    private WineryRepository wineryRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    @Transactional(readOnly = true)
    public List<Winery> findAll() {
        return this.wineryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Winery findOne(Long id) {
        Winery winery = this.wineryRepository.getOne(id);
        return winery;
    }

    @Override
    @Transactional(readOnly = false)
    public Winery save(Winery winery) {
        return this.wineryRepository.save(winery);
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(Winery winery) {
        winery.setDeleted(true);
        this.wineryRepository.save(winery);
    }

    @Override
    @Transactional(readOnly = true)
    public Winery findByWineryName(String wineryName){
        return this.wineryRepository.findByWineryName(wineryName);
    }
}
