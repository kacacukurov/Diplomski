package rs.uns.ac.ftn.Diplomski.model.drl;

import rs.uns.ac.ftn.Diplomski.model.Grape;

import java.util.List;

public class GrapeList {

    private List<Grape> grapeLists;

    public GrapeList() {
    }

    public GrapeList(List<Grape> grapeLists) {
        this.grapeLists = grapeLists;
    }

    public List<Grape> getGrapeLists() {
        return grapeLists;
    }

    public void setGrapeLists(List<Grape> grapeLists) {
        this.grapeLists = grapeLists;
    }
}
