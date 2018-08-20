package rs.uns.ac.ftn.Diplomski.web.dto;

import rs.uns.ac.ftn.Diplomski.model.enumeration.WineBody;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineColor;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineFlavor;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineSugar;

import java.util.List;

public class NewWineDTO {

    private String wineName;
    private WineBody wineBody;
    private WineColor wineColor;
    private WineSugar wineSugar;
    private WineFlavor wineFlavor;
    private Long regionId;
    private List<Long> grapeIds;
    private Long subclassOfWine;
    private Long wineryId;

    public NewWineDTO() {
    }

    public NewWineDTO(String wineName, WineBody wineBody, WineColor wineColor, WineSugar wineSugar,
                      WineFlavor wineFlavor, Long regionId, List<Long> grapeIds, Long subclassOfWine, Long wineryId) {
        this.wineName = wineName;
        this.wineBody = wineBody;
        this.wineColor = wineColor;
        this.wineSugar = wineSugar;
        this.wineFlavor = wineFlavor;
        this.regionId = regionId;
        this.grapeIds = grapeIds;
        this.subclassOfWine = subclassOfWine;
        this.wineryId = wineryId;
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

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public List<Long> getGrapeIds() {
        return grapeIds;
    }

    public void setGrapeIds(List<Long> grapeIds) {
        this.grapeIds = grapeIds;
    }

    public Long getSubclassOfWine() {
        return subclassOfWine;
    }

    public void setSubclassOfWine(Long subclassOfWine) {
        this.subclassOfWine = subclassOfWine;
    }

    public Long getWineryId() {
        return wineryId;
    }

    public void setWineryId(Long wineryId) {
        this.wineryId = wineryId;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }
}
