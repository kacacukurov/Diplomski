package rs.uns.ac.ftn.Diplomski.model.drl;

import java.util.List;

public class GrapeList {

    private List<GrapeList> grapeLists;

    public GrapeList() {
    }

    public GrapeList(List<GrapeList> grapeLists) {
        this.grapeLists = grapeLists;
    }

    public List<GrapeList> getGrapeLists() {
        return grapeLists;
    }

    public void setGrapeLists(List<GrapeList> grapeLists) {
        this.grapeLists = grapeLists;
    }
}
