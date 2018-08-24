package rs.uns.ac.ftn.Diplomski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.uns.ac.ftn.Diplomski.model.Region;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Region findByRegionName(String regionName);

    List<Region> findByLocatedin(Long locatedIn);
}
