package rs.uns.ac.ftn.Diplomski.web.dto;

import java.util.ArrayList;
import java.util.List;

public class GrapeListDTO {

    private List<GrapeDTO> grapeDTOs;

    public GrapeListDTO() {
        grapeDTOs = new ArrayList<>();
    }

    public GrapeListDTO(List<GrapeDTO> grapeDTOs) {
        this.grapeDTOs = grapeDTOs;
    }

    public List<GrapeDTO> getGrapeDTOs() {
        return grapeDTOs;
    }

    public void setGrapeDTOs(List<GrapeDTO> grapeDTOs) {
        this.grapeDTOs = grapeDTOs;
    }
}
