package rs.uns.ac.ftn.Diplomski.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.uns.ac.ftn.Diplomski.model.Grape;
import rs.uns.ac.ftn.Diplomski.model.Wine;
import rs.uns.ac.ftn.Diplomski.model.drl.GrapeList;
import rs.uns.ac.ftn.Diplomski.model.drl.PossibleWines;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineBody;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineColor;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineFlavor;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineSugar;
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

    public List<Wine> filterWines(WineBody body, WineColor color, WineSugar sugar, WineFlavor flavor){

        for(Wine w: this.wineService.findAll()) {
            kieSession.insert(w);
        }
        if(body != null)
            kieSession.insert(body);
        else{
            kieSession.insert(WineBody.LIGHT);
            kieSession.insert(WineBody.MEDIUM);
            kieSession.insert(WineBody.FULL);
        }
        if(color != null)
            kieSession.insert(color);
        else{
            kieSession.insert(WineColor.WHITE);
            kieSession.insert(WineColor.ROSE);
            kieSession.insert(WineColor.RED);
        }
        if(sugar != null)
            kieSession.insert(sugar);
        else{
            kieSession.insert(WineSugar.DRY);
            kieSession.insert(WineSugar.OFFDRY);
            kieSession.insert(WineSugar.SWEET);
        }
        if(flavor != null)
            kieSession.insert(flavor);
        else{
            kieSession.insert(WineFlavor.DELICATE);
            kieSession.insert(WineFlavor.MODERATE);
            kieSession.insert(WineFlavor.STRONG);
        }

        PossibleWines possibleWines = new PossibleWines();
        kieSession.insert(possibleWines);

        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        System.out.println(kieSession.fireAllRules());
        kieSession.destroy();
        return possibleWines.getWines();

    }
}