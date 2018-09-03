package rs.uns.ac.ftn.Diplomski.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.Diplomski.model.Grape;
import rs.uns.ac.ftn.Diplomski.model.Wine;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineBody;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineColor;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineFlavor;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineSugar;
import rs.uns.ac.ftn.Diplomski.service.DroolsService;
import rs.uns.ac.ftn.Diplomski.service.GrapeService;
import rs.uns.ac.ftn.Diplomski.service.WineService;
import rs.uns.ac.ftn.Diplomski.web.dto.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/drools")
public class DroolsController {

    @Autowired
    private DroolsService droolsService;

    @Autowired
    private WineService wineService;

    @Autowired
    private GrapeService grapeService;

    @RequestMapping(
            value = "/findWines",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getPossibleWinesFromGrapes(@RequestBody GrapeListDTO grapeListDTO) {

        List<Grape> grapes = new ArrayList<>();
        for(GrapeDTO grapeDTO: grapeListDTO.getGrapeDTOs()){
            grapes.add(this.grapeService.findOne(grapeDTO.getId()));
        }

        List<Wine> foundWines = this.droolsService.getPossibleWinesFromGrapes(grapes);

        List<WineDTO> wineDTOS = new ArrayList<>();
        for(Wine wine: foundWines)
            wineDTOS.add(new WineDTO(wine));

        return new ResponseEntity<>(wineDTOS, HttpStatus.OK);

    }

    @RequestMapping(
            value = "/findMissingGrapes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getMissingGrapesForWine(@RequestBody MissingGrapesDTO missingGrapesDTO) {

        List<Grape> grapes = new ArrayList<>();
        for(GrapeDTO grapeDTO: missingGrapesDTO.getGrapeDTOS()){
            grapes.add(this.grapeService.findOne(grapeDTO.getId()));
        }

        Wine wine = this.wineService.findOne(missingGrapesDTO.getWineDTO().getId());

        List<GrapeDTO> missingGrapes = this.droolsService.getMissingGrapesForWine(grapes, wine);

        return new ResponseEntity<>(missingGrapes, HttpStatus.OK);

    }

    @RequestMapping(
            value = "/findPotentialWineAndGrapes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getPotentialWines(@RequestBody GrapeListDTO grapeListDTO) {

        List<Grape> grapes = new ArrayList<>();
        for(GrapeDTO grapeDTO: grapeListDTO.getGrapeDTOs()){
            grapes.add(this.grapeService.findOne(grapeDTO.getId()));
        }

        List<MissingGrapesDTO> potencialWineAndGrapes = this.droolsService.getPotentialWines(grapes);

        return new ResponseEntity<>(potencialWineAndGrapes, HttpStatus.OK);

    }

    @RequestMapping(
            value = "/filterWines",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity filterWinesColor(@RequestBody FilterDTO filterDTO) {
        WineBody wineBody = null;
        WineColor wineColor = null;
        WineSugar wineSugar = null;
        WineFlavor wineFlavor = null;
        if(filterDTO.getWineBody() != null)
            wineBody = WineBody.valueOf(filterDTO.getWineBody());
        if(filterDTO.getWineColor() != null)
            wineColor = WineColor.valueOf(filterDTO.getWineColor());
        if(filterDTO.getWineFlavor() != null)
            wineFlavor = WineFlavor.valueOf(filterDTO.getWineFlavor());
        if(filterDTO.getWineSugar() != null)
            wineSugar = WineSugar.valueOf(filterDTO.getWineSugar());

        System.out.println(wineBody + " " + wineColor + " " + wineSugar + " " + wineFlavor);

        List<Wine> wines = this.droolsService.filterWines(wineBody, wineColor, wineSugar, wineFlavor);

        List<WineDTO> wineDTOS = new ArrayList<>();
        for(Wine wine: wines)
            wineDTOS.add(new WineDTO(wine));

        return new ResponseEntity<>(wineDTOS, HttpStatus.OK);
    }

}
