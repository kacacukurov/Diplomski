package rs.uns.ac.ftn.Diplomski.service;

import rs.uns.ac.ftn.Diplomski.model.Grape;

import java.util.List;

public interface GrapeService {

    List<Grape> findAll();

    Grape findOne(Long id);

    Grape save(Grape grape);

    void remove(Grape grape);
}
