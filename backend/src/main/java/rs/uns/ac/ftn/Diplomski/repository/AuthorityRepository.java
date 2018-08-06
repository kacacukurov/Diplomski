package rs.uns.ac.ftn.Diplomski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.uns.ac.ftn.Diplomski.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority save(Authority authority);

    Authority findByType(String type);
}
