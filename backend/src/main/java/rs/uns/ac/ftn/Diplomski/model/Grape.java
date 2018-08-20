package rs.uns.ac.ftn.Diplomski.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "grape")
@Where(clause="deleted=0")
public class Grape {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String grapeName;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    @Version
    private int version;

    @Column(nullable = false, columnDefinition = "BOOL DEFAULT FALSE")
    private boolean deleted;

    public Grape() {
    }

    public Grape(String grapeName) {
        this.grapeName = grapeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrapeName() {
        return grapeName;
    }

    public void setGrapeName(String grapeName) {
        this.grapeName = grapeName;
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
