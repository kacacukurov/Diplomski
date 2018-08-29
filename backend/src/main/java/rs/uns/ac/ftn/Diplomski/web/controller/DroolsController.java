package rs.uns.ac.ftn.Diplomski.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.uns.ac.ftn.Diplomski.model.Grape;
import rs.uns.ac.ftn.Diplomski.model.Wine;
import rs.uns.ac.ftn.Diplomski.service.DroolsService;
import rs.uns.ac.ftn.Diplomski.service.GrapeService;
import rs.uns.ac.ftn.Diplomski.service.WineService;
import rs.uns.ac.ftn.Diplomski.web.dto.GrapeDTO;
import rs.uns.ac.ftn.Diplomski.web.dto.GrapeListDTO;
import rs.uns.ac.ftn.Diplomski.web.dto.MissingGrapesDTO;
import rs.uns.ac.ftn.Diplomski.web.dto.WineDTO;

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
}
