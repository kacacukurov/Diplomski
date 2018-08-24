package rs.uns.ac.ftn.Diplomski.model.drl;

import java.util.List;

public class PossibleWines {

    private List<String> wines;

    public PossibleWines() {
    }

    public PossibleWines(List<String> wines) {
        this.wines = wines;
    }

    public List<String> getWines() {
        return wines;
    }

    public void setWines(List<String> wines) {
        this.wines = wines;
    }
}
