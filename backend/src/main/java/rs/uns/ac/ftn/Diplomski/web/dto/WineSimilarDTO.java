package rs.uns.ac.ftn.Diplomski.web.dto;

public class WineSimilarDTO {

    private String title;
    private String description;

    public WineSimilarDTO() {
    }

    public WineSimilarDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
