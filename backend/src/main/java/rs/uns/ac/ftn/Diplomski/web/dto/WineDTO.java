package rs.uns.ac.ftn.Diplomski.web.dto;

import rs.uns.ac.ftn.Diplomski.model.Grape;
import rs.uns.ac.ftn.Diplomski.model.Wine;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineBody;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineColor;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineFlavor;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineSugar;

import java.util.ArrayList;
import java.util.List;

public class WineDTO {

    private Long id;
    private String wineName;
    private WineBody wineBody;
    private WineColor wineColor;
    private WineSugar wineSugar;
    private WineFlavor wineFlavor;
    private RegionDTO regionDTO;
    private List<GrapeDTO> grapeDTOS;
    private Long subclassOfWine;
    private WineryDTO wineryDTO;

    public WineDTO() {
    }

    public WineDTO(Long id, String wineName, WineBody wineBody, WineColor wineColor, WineSugar wineSugar,
                   WineFlavor wineFlavor, RegionDTO regionDTO, List<GrapeDTO> grapeDTOS, Long subclassOfWine,
                   WineryDTO wineryDTO) {
        this.id = id;
        this.wineName = wineName;
        this.wineBody = wineBody;
        this.wineColor = wineColor;
        this.wineSugar = wineSugar;
        this.wineFlavor = wineFlavor;
        this.regionDTO = regionDTO;
        this.grapeDTOS = grapeDTOS;
        this.subclassOfWine = subclassOfWine;
        this.wineryDTO = wineryDTO;
    }

    public WineDTO(Wine wine){
        this.id = wine.getId();
        this.wineName = wine.getWineName();
        this.wineBody = wine.getWineBody();
        this.wineColor = wine.getWineColor();
        this.wineSugar = wine.getWineSugar();
        this.wineFlavor = wine.getWineFlavor();
        this.regionDTO = new RegionDTO(wine.getRegion());
        this.subclassOfWine = wine.getSubclassOfWine();
        this.wineryDTO = new WineryDTO(wine.getWinery());
        this.grapeDTOS = new ArrayList<>();
        for(Grape grape: wine.getGrapes())
            grapeDTOS.add(new GrapeDTO(grape));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public WineBody getWineBody() {
        return wineBody;
    }

    public void setWineBody(WineBody wineBody) {
        this.wineBody = wineBody;
    }

    public WineColor getWineColor() {
        return wineColor;
    }

    public void setWineColor(WineColor wineColor) {
        this.wineColor = wineColor;
    }

    public WineSugar getWineSugar() {
        return wineSugar;
    }

    public void setWineSugar(WineSugar wineSugar) {
        this.wineSugar = wineSugar;
    }

    public WineFlavor getWineFlavor() {
        return wineFlavor;
    }

    public void setWineFlavor(WineFlavor wineFlavor) {
        this.wineFlavor = wineFlavor;
    }

    public RegionDTO getRegionDTO() {
        return regionDTO;
    }

    public void setRegionDTO(RegionDTO regionDTO) {
        this.regionDTO = regionDTO;
    }

    public List<GrapeDTO> getGrapeDTOS() {
        return grapeDTOS;
    }

    public void setGrapeDTOS(List<GrapeDTO> grapeDTOS) {
        this.grapeDTOS = grapeDTOS;
    }

    public Long getSubclassOfWine() {
        return subclassOfWine;
    }

    public void setSubclassOfWine(Long subclassOfWine) {
        this.subclassOfWine = subclassOfWine;
    }

    public WineryDTO getWineryDTO() {
        return wineryDTO;
    }

    public void setWineryDTO(WineryDTO wineryDTO) {
        this.wineryDTO = wineryDTO;
    }
}
