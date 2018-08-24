package rs.uns.ac.ftn.Diplomski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.uns.ac.ftn.Diplomski.model.Wine;

import java.util.List;

public interface WineRepository extends JpaRepository<Wine, Long> {

    Wine findByWineName(String wineName);

    List<Wine> findBySubclassOfWine(Long id);
}
