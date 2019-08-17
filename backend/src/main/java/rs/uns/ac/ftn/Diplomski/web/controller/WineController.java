package rs.uns.ac.ftn.Diplomski.web.controller;

import com.opencsv.CSVReader;
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
import rs.uns.ac.ftn.Diplomski.web.dto.WineSimilarDTO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * Method that receives data needed for new wine, calls wine service which saves that wine to database.
     * @param newWineDTO new wine data
     */
    @RequestMapping(
            value = "/new",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity addNewWine(@RequestBody NewWineDTO newWineDTO) {

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

        for(String name: newWineDTO.getGrapeNames()){
            Grape grape = this.grapeService.findByGrapeName(name);
            if(grape == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            grapes.add(grape);
        }

        wine = new Wine(newWineDTO.getWineName(), newWineDTO.getWineBody(), newWineDTO.getWineColor(),
                newWineDTO.getWineSugar(), newWineDTO.getWineFlavor(), region, grapes, newWineDTO.getSubclassOfWine(),
                winery);

        this.wineService.save(wine);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Method that receives changed data of one existing wine, calls wine service which then saves changes
     * to database.
     * @param wineDTO changed wine data
     * @return wineDTO
     */
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
        }else
            wine.setRegion(null);

        wine.setGrapes(new ArrayList<>());
        for(GrapeDTO grapeDTO: wineDTO.getGrapeDTOS()){
            Grape grape = this.grapeService.findOne(grapeDTO.getId());
            if(grape == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            wine.getGrapes().add(grape);
        }

        if(wineDTO.getSubclassOfWine() != null){
            Wine subclassOfWine = this.wineService.findByWineName(wineDTO.getSubclassOfWine());
            if(subclassOfWine == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            wine.setSubclassOfWine(subclassOfWine.getId());
        }else
            wine.setSubclassOfWine(null);

        if(wineDTO.getWineryDTO() != null){
            Winery winery = this.wineryService.findOne(wineDTO.getWineryDTO().getId());
            if(winery == null)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            wine.setWinery(winery);
        }
        else
            wine.setWinery(null);

        this.wineService.save(wine);
        return new ResponseEntity<>(wineDTO, HttpStatus.CREATED);
    }

    /**
     * Method that returns list of all wines from database.
     * @return wineDTOS
     */
    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAllWines() {
        List<Wine> wines = this.wineService.findAll();
        List<WineDTO> wineDTOS = new ArrayList<>();
        for(Wine wine: wines) {
            WineDTO wineDTO = new WineDTO(wine);
            if(wine.getSubclassOfWine() != null)
                wineDTO.setSubclassOfWine(this.wineService.findOne(wine.getSubclassOfWine()).getWineName());
            List<Wine> subwines =  this.wineService.findBySubclassOfWine(wine.getId());
            if(subwines.isEmpty())
                wineDTO.setSuperClass(false);
            else
                wineDTO.setSuperClass(true);
            wineDTOS.add(wineDTO);
        }
        return new ResponseEntity<>(wineDTOS, HttpStatus.OK);
    }

    /**
     * Method that receives wine id and calls wine service which than deletes corresponding wine.
     * @param id wine id
     */
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

    /**
     * Method that returns list of similar wines to chosen wine.
     * @return List<WineSimilarDTO>
     */
    @RequestMapping(
            value = "/similar/{wineName}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAllSimilarWines(@PathVariable("wineName") String wineName) throws NullPointerException, IOException{
        List<WineSimilarDTO> similarWinesDTO = getBestSimilarWines(wineName.toLowerCase());
        return new ResponseEntity<>(similarWinesDTO, HttpStatus.OK);
    }

    private List<WineSimilarDTO> getBestSimilarWines(String wineName) throws NullPointerException, IOException{
        if(wineName.contains("white") || wineName.contains("red")){
            wineName = wineName.split(" ")[1];
        }

        List<WineSimilarDTO> mostSimilarWines = new ArrayList<>();
        double percentage = 0;

        for(int i = 0; i < 11; i++){
            List<WineSimilarDTO> similarDTOS = new ArrayList<>();
            // load from file
            List<List<String>> records = new ArrayList<List<String>>();
            URL url = ClassLoader.getSystemClassLoader().getResource("clusters/cluster_" + i + ".csv");
            try (CSVReader csvReader = new CSVReader(new FileReader(url.getFile()))) {
                String[] values = null;
                while ((values = csvReader.readNext()) != null) {
                    records.add(Arrays.asList(values));
                }
            }
            records.remove(0);
            records.forEach(record -> similarDTOS.add(new WineSimilarDTO(record.get(12), record.get(3))));
            System.out.println("klasteer  " + similarDTOS.size());
            // calculate percentage
            int total = 0;
            for(WineSimilarDTO wine: similarDTOS){
                if(wine.getTitle().toLowerCase().contains(wineName))
                    total += 1;
            }
            double new_percentage = ((double)total / similarDTOS.size()) * 100;
            System.out.println(new_percentage);
            if(new_percentage > percentage) {
                percentage = new_percentage;
                mostSimilarWines = new ArrayList<>();
                for (WineSimilarDTO wine : similarDTOS) {
                    mostSimilarWines.add(wine);
                }
            }
            System.out.println(mostSimilarWines.size());
        }

        return mostSimilarWines;
    }
}
