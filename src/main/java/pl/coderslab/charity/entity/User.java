package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.entity.generic.GenericEntityID;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
public class User extends GenericEntityID {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") })
    private Set<Authority> authorities;

    public User () {
        this.authorities = new HashSet<>();
    }

}