package rs.uns.ac.ftn.Diplomski.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.Diplomski.model.Grape;
import rs.uns.ac.ftn.Diplomski.service.GrapeService;
import rs.uns.ac.ftn.Diplomski.web.dto.GrapeDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/grape")
public class GrapeController {

    @Autowired
    private GrapeService grapeService;

    /**
     * Method that receives grape name and calls grape service which makes new grape in database.
     * @param grapeName grape name
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addNewGrape(@RequestBody String grapeName){
        Grape grape = this.grapeService.findByGrapeName(grapeName);
        if(grape != null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        grape = new Grape(grapeName);

        this.grapeService.save(grape);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Method that returns list of all grapes from database.
     * @return grapeDTOS
     */
    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAllGrapes() {
        List<Grape> grapes = this.grapeService.findAll();
        List<GrapeDTO> grapeDTOS = new ArrayList<>();
        for(Grape grape: grapes)
            grapeDTOS.add(new GrapeDTO(grape));
        return new ResponseEntity<>(grapeDTOS, HttpStatus.OK);
    }

    /**
     * Method that receives grape id and calls grape service which than deletes corresponding grape.
     * @param id grape id
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteGrape(@RequestParam Long id){
        Grape grape = this.grapeService.findOne(id);
        if(grape == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        this.grapeService.remove(grape);
        return new ResponseEntity(HttpStatus.OK);
    }
}
