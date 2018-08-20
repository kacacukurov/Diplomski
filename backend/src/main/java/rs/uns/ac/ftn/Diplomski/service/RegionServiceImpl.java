package rs.uns.ac.ftn.Diplomski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.uns.ac.ftn.Diplomski.model.Region;
import rs.uns.ac.ftn.Diplomski.repository.RegionRepository;
import rs.uns.ac.ftn.Diplomski.security.JWTUtils;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAll() {
        return this.regionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Region findOne(Long id) {
        Region region = this.regionRepository.getOne(id);
        return region;
    }

    @Override
    @Transactional(readOnly = false)
    public Region save(Region region) {
        return this.regionRepository.save(region);
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(Region region) {
        region.setDeleted(true);
        this.regionRepository.save(region);
    }

    @Override
    @Transactional(readOnly = true)
    public Region findByRegionName(String regionName){
        return this.regionRepository.findByRegionName(regionName);
    }
}
