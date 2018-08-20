package rs.uns.ac.ftn.Diplomski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.uns.ac.ftn.Diplomski.model.Wine;

public interface WineRepository extends JpaRepository<Wine, Long> {
}
