package rs.uns.ac.ftn.Diplomski.service;

import rs.uns.ac.ftn.Diplomski.model.Authority;

public interface AuthorityService {

    Authority save(Authority authority);

    void remove(Long id);

    Authority findByType(String type);
}
