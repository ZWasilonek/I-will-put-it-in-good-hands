package pl.coderslab.charity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.coderslab.charity.entity.generic.GenericEntityID;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@Data
public class User extends GenericEntityID {

    private String firstName;
    private String lastName;
    private String password;
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") })
    private Set<Authority> authorities;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_donations",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "donation_id") })
    private Set<Donation> donations;

    public User () {
        this.authorities = new HashSet<>();
    }
}
