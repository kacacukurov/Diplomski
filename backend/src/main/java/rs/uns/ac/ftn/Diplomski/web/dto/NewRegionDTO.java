package rs.uns.ac.ftn.Diplomski.web.dto;

public class NewRegionDTO {

    private String regionName;
    private Long locatedIn;

    public NewRegionDTO() {
    }

    public NewRegionDTO(String regionName, Long locatedIn) {
        this.regionName = regionName;
        this.locatedIn = locatedIn;
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
