package rs.uns.ac.ftn.Diplomski.service;

import rs.uns.ac.ftn.Diplomski.model.Winery;

import java.util.List;

public interface WineryService {

    List<Winery> findAll();

    Winery findOne(Long id);

    Winery save(Winery winery);

    void remove(Winery winery);

    Winery findByWineryName(String wineryName);
}
