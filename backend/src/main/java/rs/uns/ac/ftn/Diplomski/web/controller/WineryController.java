package rs.uns.ac.ftn.Diplomski.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.Diplomski.model.Winery;
import rs.uns.ac.ftn.Diplomski.service.WineryService;
import rs.uns.ac.ftn.Diplomski.web.dto.WineryDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/winery")
public class WineryController {

    @Autowired
    private WineryService wineryService;

    @RequestMapping(
            value = "/new",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addNewWinery(@RequestBody String wineryName){
        Winery winery = this.wineryService.findByWineryName(wineryName);
        if(winery != null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        winery = new Winery(wineryName);
        this.wineryService.save(winery);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAllWineries() {
        List<Winery> wineries = this.wineryService.findAll();
        List<WineryDTO> wineryDTOS = new ArrayList<>();
        for(Winery winery: wineries)
            wineryDTOS.add(new WineryDTO(winery));

        return new ResponseEntity<>(wineryDTOS, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteWinery(@RequestParam Long id){
        Winery winery = this.wineryService.findOne(id);

        if(winery == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        this.wineryService.remove(winery);
        return new ResponseEntity(HttpStatus.OK);
    }
}
