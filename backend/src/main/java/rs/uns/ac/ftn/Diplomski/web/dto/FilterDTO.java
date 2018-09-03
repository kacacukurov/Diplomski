package rs.uns.ac.ftn.Diplomski.web.dto;

public class FilterDTO {

    private String wineBody;
    private String wineColor;
    private String wineSugar;
    private String wineFlavor;

    public FilterDTO() {
    }

    public FilterDTO(String wineBody, String wineColor, String wineSugar, String wineFlavor) {
        this.wineBody = wineBody;
        this.wineColor = wineColor;
        this.wineSugar = wineSugar;
        this.wineFlavor = wineFlavor;
    }

    public String getWineBody() {
        return wineBody;
    }

    public void setWineBody(String wineBody) {
        this.wineBody = wineBody;
    }

    public String getWineColor() {
        return wineColor;
    }

    public void setWineColor(String wineColor) {
        this.wineColor = wineColor;
    }

    public String getWineSugar() {
        return wineSugar;
    }

    public void setWineSugar(String wineSugar) {
        this.wineSugar = wineSugar;
    }

    public String getWineFlavor() {
        return wineFlavor;
    }

    public void setWineFlavor(String wineFlavor) {
        this.wineFlavor = wineFlavor;
    }
}
