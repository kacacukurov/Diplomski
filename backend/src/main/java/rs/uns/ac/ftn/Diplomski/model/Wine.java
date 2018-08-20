package rs.uns.ac.ftn.Diplomski.model;

import org.hibernate.annotations.Where;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineBody;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineColor;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineFlavor;
import rs.uns.ac.ftn.Diplomski.model.enumeration.WineSugar;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wine")
@Where(clause="deleted=0")
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String wineName;

    @Column
    private WineBody wineBody;

    @Column
    private WineColor wineColor;

    @Column
    private WineSugar wineSugar;

    @Column
    private WineFlavor wineFlavor;

    @ManyToOne
    private Region region;

    @ManyToMany
    private List<Grape> grapes;

    @Column
    private Long subclassOfWine;

    @ManyToOne
    private Winery winery;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    @Version
    private int version;

    @Column(nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
    private boolean deleted;

    public Wine() {
        this.grapes = new ArrayList<>();
    }

    public Wine(String wineName, WineBody wineBody, WineColor wineColor, WineSugar wineSugar, WineFlavor wineFlavor,
                Region region, List<Grape> grapes, Long subclassOfWine, Winery winery) {
        this.wineName = wineName;
        this.wineBody = wineBody;
        this.wineColor = wineColor;
        this.wineSugar = wineSugar;
        this.wineFlavor = wineFlavor;
        this.region = region;
        this.grapes = grapes;
        this.subclassOfWine = subclassOfWine;
        this.winery = winery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WineBody getWineBody() {
        return wineBody;
    }

    public void setWineBody(WineBody wineBody) {
        this.wineBody = wineBody;
    }

    public WineColor getWineColor() {
        return wineColor;
    }

    public void setWineColor(WineColor wineColor) {
        this.wineColor = wineColor;
    }

    public WineSugar getWineSugar() {
        return wineSugar;
    }

    public void setWineSugar(WineSugar wineSugar) {
        this.wineSugar = wineSugar;
    }

    public WineFlavor getWineFlavor() {
        return wineFlavor;
    }

    public void setWineFlavor(WineFlavor wineFlavor) {
        this.wineFlavor = wineFlavor;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Grape> getGrapes() {
        return grapes;
    }

    public void setGrapes(List<Grape> grapes) {
        this.grapes = grapes;
    }

    public Winery getWinery() {
        return winery;
    }

    public void setWinery(Winery winery) {
        this.winery = winery;
    }

    public Long getSubclassOfWine() {
        return subclassOfWine;
    }

    public void setSubclassOfWine(Long subclassOfWine) {
        this.subclassOfWine = subclassOfWine;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }
}