package rs.uns.ac.ftn.Diplomski.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "winery")
@Where(clause="deleted=0")
public class Winery {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String wineryName;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    @Version
    private int version;

    @Column(nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
    private boolean deleted;

    public Winery() {
    }

    public Winery(String wineryName) {
        this.wineryName = wineryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWineryName() {
        return wineryName;
    }

    public void setWineryName(String wineryName) {
        this.wineryName = wineryName;
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
