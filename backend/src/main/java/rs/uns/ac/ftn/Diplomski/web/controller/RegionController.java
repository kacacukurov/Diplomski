package rs.uns.ac.ftn.Diplomski.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.uns.ac.ftn.Diplomski.model.Region;
import rs.uns.ac.ftn.Diplomski.service.RegionService;
import rs.uns.ac.ftn.Diplomski.web.dto.NewRegionDTO;
import rs.uns.ac.ftn.Diplomski.web.dto.RegionDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(
            value = "/new",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addNewRegion(@RequestBody NewRegionDTO newRegionDTO) {

        Region region = this.regionService.findByRegionName(newRegionDTO.getRegionName());
        if(region != null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        if(newRegionDTO.getLocatedIn() != null){
            Region locatedIn = this.regionService.findOne(newRegionDTO.getLocatedIn());
            if(locatedIn == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        region = new Region(newRegionDTO.getRegionName(), newRegionDTO.getLocatedIn());

        this.regionService.save(region);
        return new ResponseEntity<>(newRegionDTO, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/change",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity changeRegion(@RequestBody RegionDTO regionDTO) {

        Region region = this.regionService.findOne(regionDTO.getId());
        Region region1 = this.regionService.findByRegionName(regionDTO.getRegionName());

        if(region == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        if((region1 != null) && !(region1.getId().equals(region.getId())))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        if(regionDTO.getLocatedIn() != null){
            Region locatedIn = this.regionService.findOne(regionDTO.getLocatedIn());
            if(locatedIn == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        region.setLocatedIn(regionDTO.getLocatedIn());
        region.setRegionName(regionDTO.getRegionName());
        
        this.regionService.save(region);
        return new ResponseEntity<>(regionDTO, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAllGrapes() {
        List<Region> regions = this.regionService.findAll();
        List<RegionDTO> regionDTOS = new ArrayList<>();
        for(Region region: regions)
            regionDTOS.add(new RegionDTO(region));
        return new ResponseEntity<>(regionDTOS, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity deleteRegion(@RequestParam Long id){
        Region region = this.regionService.findOne(id);
        if(region == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        this.regionService.remove(region);
        return new ResponseEntity(HttpStatus.OK);
    }
}
