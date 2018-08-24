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
    private Long locatedin;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    @Version
    private int version;

    @Column(nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
    private boolean deleted;

    public Region() {
    }

    public Region(String regionName, Long locatedin) {
        this.regionName = regionName;
        this.locatedin = locatedin;
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

    public Long getLocatedin() {
        return locatedin;
    }

    public void setLocatedin(Long locatedin) {
        this.locatedin = locatedin;
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
