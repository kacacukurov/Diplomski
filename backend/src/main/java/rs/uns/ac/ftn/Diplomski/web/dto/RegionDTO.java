package rs.uns.ac.ftn.Diplomski.web.dto;


import rs.uns.ac.ftn.Diplomski.model.Region;

public class RegionDTO {

    private Long id;
    private String regionName;
    private String locatedIn;
    private boolean isSuperRegion;

    public RegionDTO(){

    }

    public RegionDTO(Long id, String regionName, String locatedIn, boolean isSuperRegion) {
        this.id = id;
        this.regionName = regionName;
        this.locatedIn = locatedIn;
        this.isSuperRegion = isSuperRegion;
    }

    public RegionDTO(Region region){
        this.id = region.getId();
        this.regionName = region.getRegionName();
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

    public String getLocatedIn() {
        return locatedIn;
    }

    public void setLocatedIn(String locatedIn) {
        this.locatedIn = locatedIn;
    }

    public boolean isSuperRegion() {
        return isSuperRegion;
    }

    public void setSuperRegion(boolean superRegion) {
        isSuperRegion = superRegion;
    }
}
