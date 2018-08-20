package rs.uns.ac.ftn.Diplomski.web.dto;

import rs.uns.ac.ftn.Diplomski.model.Grape;

public class GrapeDTO {

    private String grapeName;
    private Long id;

    public GrapeDTO(){}

    public GrapeDTO(String grapeName, Long id) {
        this.grapeName = grapeName;
        this.id = id;
    }

    public GrapeDTO(Grape grape){
        this.grapeName = grape.getGrapeName();
        this.id = grape.getId();
    }

    public String getGrapeName() {
        return grapeName;
    }

    public void setGrapeName(String grapeName) {
        this.grapeName = grapeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
