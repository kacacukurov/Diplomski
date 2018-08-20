package rs.uns.ac.ftn.Diplomski.web.dto;

public class TokenDTO {

    private String value;

    public TokenDTO() {
    }

    public TokenDTO(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
