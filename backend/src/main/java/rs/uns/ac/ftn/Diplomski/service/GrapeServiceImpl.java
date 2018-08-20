package rs.uns.ac.ftn.Diplomski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.Diplomski.model.Grape;
import rs.uns.ac.ftn.Diplomski.repository.GrapeRepository;
import rs.uns.ac.ftn.Diplomski.security.JWTUtils;

import java.util.List;

@Service
public class GrapeServiceImpl implements GrapeService {

    @Autowired
    private GrapeRepository grapeRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    @Transactional(readOnly = true)
    public List<Grape> findAll() {
        return this.grapeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Grape findOne(Long id) {
        Grape grape = this.grapeRepository.getOne(id);
        return grape;
    }

    @Override
    @Transactional(readOnly = false)
    public Grape save(Grape grape) {
        return this.grapeRepository.save(grape);
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(Grape grape) {
        grape.setDeleted(true);
        this.grapeRepository.save(grape);
    }

    @Override
    @Transactional(readOnly = true)
    public Grape findByGrapeName(String grapeName){
        return this.grapeRepository.findByGrapeName(grapeName);
    }
}
