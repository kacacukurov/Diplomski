package rs.uns.ac.ftn.Diplomski.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.Diplomski.model.Grape;
import rs.uns.ac.ftn.Diplomski.model.Region;
import rs.uns.ac.ftn.Diplomski.model.Wine;
import rs.uns.ac.ftn.Diplomski.model.Winery;
import rs.uns.ac.ftn.Diplomski.service.GrapeService;
import rs.uns.ac.ftn.Diplomski.service.RegionService;
import rs.uns.ac.ftn.Diplomski.service.WineService;
import rs.uns.ac.ftn.Diplomski.service.WineryService;
import rs.uns.ac.ftn.Diplomski.web.dto.GrapeDTO;
import rs.uns.ac.ftn.Diplomski.web.dto.NewWineDTO;
import rs.uns.ac.ftn.Diplomski.web.dto.WineDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/wine")
public class WineController {

    @Autowired
    private WineService wineService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private WineryService wineryService;

    @Autowired
    private GrapeService grapeService;

    @RequestMapping(
            value = "/new",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addNewRegion(@RequestBody NewWineDTO newWineDTO) {

        Wine wine = this.wineService.findByWineName(newWineDTO.getWineName());
        if(wine != null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Region region = null;
        Winery winery = null;
        List<Grape> grapes = new ArrayList<>();

        if(newWineDTO.getRegionId() != null){
            region = this.regionService.findOne(newWineDTO.getRegionId());
            if(region == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(newWineDTO.getSubclassOfWine() != null){
            Wine subclassOf = this.wineService.findOne(newWineDTO.getSubclassOfWine());
            if(subclassOf == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if(newWineDTO.getWineryId() != null){
            winery = this.wineryService.findOne(newWineDTO.getWineryId());
            if(winery == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        for(Long id: newWineDTO.getGrapeIds()){
            Grape grape = this.grapeService.findOne(id);
            if(grape == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            grapes.add(grape);
        }

        wine = new Wine(newWineDTO.getWineName(), newWineDTO.getWineBody(), newWineDTO.getWineColor(),
                newWineDTO.getWineSugar(), newWineDTO.getWineFlavor(), region, grapes, newWineDTO.getSubclassOfWine(),
                winery);

        this.wineService.save(wine);
        return new ResponseEntity<>(newWineDTO, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/change",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity changeWine(@RequestBody WineDTO wineDTO) {

        Wine wine = this.wineService.findOne(wineDTO.getId());
        Wine wine1 = this.wineService.findByWineName(wineDTO.getWineName());

        if(wine == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        if((wine1 != null) && !(wine.getId().equals(wine1.getId())))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        wine.setWineName(wineDTO.getWineName());
        wine.setWineBody(wineDTO.getWineBody());
        wine.setWineColor(wineDTO.getWineColor());
        wine.setWineSugar(wineDTO.getWineSugar());
        wine.setWineFlavor(wineDTO.getWineFlavor());

        if(wineDTO.getRegionDTO() != null){
            Region region = this.regionService.findOne(wineDTO.getRegionDTO().getId());
            if(region == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            wine.setRegion(region);
        }

        wine.setGrapes(new ArrayList<>());
        for(GrapeDTO grapeDTO: wineDTO.getGrapeDTOS()){
            Grape grape = this.grapeService.findOne(grapeDTO.getId());
            if(grape == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            wine.getGrapes().add(grape);
        }

        if(wineDTO.getSubclassOfWine() != null){
            Wine subclassOfWine = this.wineService.findOne(wineDTO.getSubclassOfWine());
            if(subclassOfWine == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            wine.setSubclassOfWine(wineDTO.getSubclassOfWine());
        }

        if(wineDTO.getWineryDTO() != null){
            Winery winery = this.wineryService.findOne(wineDTO.getWineryDTO().getId());
            if(winery == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            wine.setWinery(winery);
        }

        this.wineService.save(wine);
        return new ResponseEntity<>(wineDTO, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAllWines() {
        List<Wine> wines = this.wineService.findAll();
        List<WineDTO> wineDTOS = new ArrayList<>();
        for(Wine wine: wines)
            wineDTOS.add(new WineDTO(wine));
        return new ResponseEntity<>(wineDTOS, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteWine(@RequestParam Long id){
        Wine wine = this.wineService.findOne(id);
        if(wine == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        this.wineService.remove(wine);
        return new ResponseEntity(HttpStatus.OK);
    }
}
