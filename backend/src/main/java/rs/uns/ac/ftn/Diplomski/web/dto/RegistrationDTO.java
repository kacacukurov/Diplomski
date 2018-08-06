package rs.uns.ac.ftn.Diplomski.web.dto;

public class RegistrationDTO {

    private LoginDTO loginAccount;

    private String firstName;

    private String lastName;

    public RegistrationDTO(){}

    public RegistrationDTO(LoginDTO loginAccount, String firstName, String lastName) {
        this.loginAccount = loginAccount;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public LoginDTO getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(LoginDTO loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
