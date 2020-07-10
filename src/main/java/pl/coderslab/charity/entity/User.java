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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") })
    private Set<Authority> authorities;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_donations",
//            joinColumns = { @JoinColumn(name = "user_id") },
//            inverseJoinColumns = { @JoinColumn(name = "donation_id") })
//    private Set<Donation> donations;

    public User () {
//        this.donations = new HashSet<>();
        this.authorities = new HashSet<>();
    }

}