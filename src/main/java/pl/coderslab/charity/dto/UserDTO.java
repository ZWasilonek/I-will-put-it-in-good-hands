package pl.coderslab.charity.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Component
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private boolean isEnabled;
    private Set<AuthorityDTO> authorities;
    private Set<DonationDTO> donations;

    public UserDTO () {
        this.donations = new HashSet<>();
        this.authorities = new HashSet<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toUpperCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase();
    }

}