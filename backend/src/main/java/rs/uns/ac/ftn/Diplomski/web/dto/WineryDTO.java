package rs.uns.ac.ftn.Diplomski.web.dto;

import rs.uns.ac.ftn.Diplomski.model.Winery;

public class WineryDTO {

    private Long id;
    private String wineryName;

    public WineryDTO(){}

    public WineryDTO(Long id, String wineryName) {
        this.id = id;
        this.wineryName = wineryName;
    }

    public WineryDTO(Winery winery){
        this.id = winery.getId();
        this.wineryName = winery.getWineryName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWineryName() {
        return wineryName;
    }

    public void setWineryName(String wineryName) {
        this.wineryName = wineryName;
    }
}
