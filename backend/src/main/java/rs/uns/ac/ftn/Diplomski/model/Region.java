package rs.uns.ac.ftn.Diplomski.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "region")
@Where(clause="deleted=0")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String regionName;

    @Column
    private Long locatedIn;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    @Version
    private int version;

    @Column(nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
    private boolean deleted;

    public Region() {
    }

    public Region(String regionName, Long locatedIn) {
        this.regionName = regionName;
        this.locatedIn = locatedIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
