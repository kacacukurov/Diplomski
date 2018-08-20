package rs.uns.ac.ftn.Diplomski.web.dto;


import rs.uns.ac.ftn.Diplomski.model.Region;

public class RegionDTO {

    private Long id;
    private String regionName;
    private Long locatedIn;

    public RegionDTO(){

    }

    public RegionDTO(Long id, String regionName, Long locatedIn) {
        this.id = id;
        this.regionName = regionName;
        this.locatedIn = locatedIn;
    }

    public RegionDTO(Region region){
        this.id = region.getId();
        this.regionName = region.getRegionName();
        this.locatedIn = region.getLocatedIn();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Long getLocatedIn() {
        return locatedIn;
    }

    public void setLocatedIn(Long locatedIn) {
        this.locatedIn = locatedIn;
    }
}
