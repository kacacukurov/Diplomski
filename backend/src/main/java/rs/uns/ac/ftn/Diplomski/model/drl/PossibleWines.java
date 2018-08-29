package rs.uns.ac.ftn.Diplomski.model.drl;

import rs.uns.ac.ftn.Diplomski.model.Wine;

import java.util.ArrayList;
import java.util.List;

public class PossibleWines {

    private List<Wine> wines;

    public PossibleWines() {
        this.wines = new ArrayList<>();
    }

    public PossibleWines(List<Wine> wines) {
        this.wines = wines;
    }

    public List<Wine> getWines() {
        return wines;
    }

    public void setWines(List<Wine> wines) {
        this.wines = wines;
    }
}
