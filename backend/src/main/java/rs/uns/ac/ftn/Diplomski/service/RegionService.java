package rs.uns.ac.ftn.Diplomski.service;

import rs.uns.ac.ftn.Diplomski.model.Region;

import java.util.List;

public interface RegionService {

    List<Region> findAll();

    Region findOne(Long id);

    Region save(Region region);

    void remove(Region region);
}
