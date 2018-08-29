package rs.uns.ac.ftn.Diplomski.web.dto;

import java.util.ArrayList;
import java.util.List;

public class PotentialWinesDTO {

    private List<MissingGrapesDTO> missingGrapesDTOS;

    public PotentialWinesDTO() {
        missingGrapesDTOS = new ArrayList<>();
    }

    public PotentialWinesDTO(List<MissingGrapesDTO> missingGrapesDTOS) {
        this.missingGrapesDTOS = missingGrapesDTOS;
    }

    public List<MissingGrapesDTO> getMissingGrapesDTOS() {
        return missingGrapesDTOS;
    }

    public void setMissingGrapesDTOS(List<MissingGrapesDTO> missingGrapesDTOS) {
        this.missingGrapesDTOS = missingGrapesDTOS;
    }
}
