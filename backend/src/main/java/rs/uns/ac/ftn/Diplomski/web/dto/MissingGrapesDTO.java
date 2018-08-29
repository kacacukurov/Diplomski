package rs.uns.ac.ftn.Diplomski.web.dto;

import java.util.ArrayList;
import java.util.List;

public class MissingGrapesDTO {

    private List<GrapeDTO> grapeDTOS;
    private WineDTO wineDTO;

    public MissingGrapesDTO() {
        grapeDTOS = new ArrayList<>();
    }

    public MissingGrapesDTO(List<GrapeDTO> grapeDTOS, WineDTO wineDTO) {
        this.grapeDTOS = grapeDTOS;
        this.wineDTO = wineDTO;
    }

    public List<GrapeDTO> getGrapeDTOS() {
        return grapeDTOS;
    }

    public void setGrapeDTOS(List<GrapeDTO> grapeDTOS) {
        this.grapeDTOS = grapeDTOS;
    }

    public WineDTO getWineDTO() {
        return wineDTO;
    }

    public void setWineDTO(WineDTO wineDTO) {
        this.wineDTO = wineDTO;
    }
}
