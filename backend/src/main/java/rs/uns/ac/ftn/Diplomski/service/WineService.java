package rs.uns.ac.ftn.Diplomski.service;

import rs.uns.ac.ftn.Diplomski.model.Wine;

import java.util.List;

public interface WineService {

    List<Wine> findAll();

    Wine findOne(Long id);

    Wine save(Wine wine);

    void remove(Wine wine);
}
