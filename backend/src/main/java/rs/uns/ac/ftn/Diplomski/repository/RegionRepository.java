package rs.uns.ac.ftn.Diplomski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.uns.ac.ftn.Diplomski.model.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Region findByRegionName(String regionName);
}
