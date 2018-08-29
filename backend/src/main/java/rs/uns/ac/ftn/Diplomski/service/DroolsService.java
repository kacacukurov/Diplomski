package rs.uns.ac.ftn.Diplomski.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.uns.ac.ftn.Diplomski.model.Grape;
import rs.uns.ac.ftn.Diplomski.model.Wine;
import rs.uns.ac.ftn.Diplomski.model.drl.GrapeList;
import rs.uns.ac.ftn.Diplomski.model.drl.PossibleWines;
import rs.uns.ac.ftn.Diplomski.web.dto.GrapeDTO;
import rs.uns.ac.ftn.Diplomski.web.dto.GrapeListDTO;
import rs.uns.ac.ftn.Diplomski.web.dto.MissingGrapesDTO;
import rs.uns.ac.ftn.Diplomski.web.dto.PotentialWinesDTO;


import java.util.List;
@Service
public class DroolsService {

    private final KieSession kieSession;

    @Autowired
    private WineService wineService;

    @Autowired
    private GrapeService grapeService;


    @Autowired
    public DroolsService(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    public List<Wine> getPossibleWinesFromGrapes(List<Grape> grapes){

        PossibleWines possibleWines = new PossibleWines();
        List<Wine> allWines = this.wineService.findAll();
        GrapeList grapeList = new GrapeList(grapes);
        kieSession.insert(possibleWines);
        kieSession.insert(grapeList);
        for(Wine w: allWines)
            kieSession.insert(w);

        kieSession.getAgenda().getAgendaGroup("wines").setFocus();
        System.out.println(kieSession.fireAllRules());
        kieSession.destroy();

        return possibleWines.getWines();
    }

    public List<GrapeDTO> getMissingGrapesForWine(List<Grape> grapes, Wine wine){

        kieSession.insert(wine);
        GrapeList grapeList = new GrapeList(grapes);
        kieSession.insert(grapeList);
        for(Grape g: this.grapeService.findAll())
            kieSession.insert(g);
        GrapeListDTO grapeListDTO = new GrapeListDTO();
        kieSession.insert(grapeListDTO);

        kieSession.getAgenda().getAgendaGroup("missing-grapes").setFocus();
        System.out.println(kieSession.fireAllRules());
        kieSession.destroy();

        return grapeListDTO.getGrapeDTOs();
    }

    public List<MissingGrapesDTO> getPotentialWines(List<Grape> grapes){

        PotentialWinesDTO potentialWinesDTO = new PotentialWinesDTO();
        kieSession.insert(potentialWinesDTO);
        GrapeList grapeList = new GrapeList(grapes);
        kieSession.insert(grapeList);
        for(Wine w: this.wineService.findAll())
            kieSession.insert(w);
        for(Grape g: this.grapeService.findAll())
            kieSession.insert(g);

        kieSession.getAgenda().getAgendaGroup("potential-wines").setFocus();
        System.out.println(kieSession.fireAllRules());
        kieSession.destroy();

        return potentialWinesDTO.getMissingGrapesDTOS();
    }
}
