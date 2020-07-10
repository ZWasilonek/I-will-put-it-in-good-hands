package pl.coderslab.charity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.entity.generic.GenericEntityID;
import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity(name = "donations")
@Getter
@Setter
@AllArgsConstructor
public class Donation extends GenericEntityID {

    private Integer bagsQuantity;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;

    @ManyToOne
    private Institution institution;

    @ManyToOne(cascade = CascadeType.ALL)
    private ShippingAddress shippingAddress;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    public Donation() {
        categories = new TreeSet<>();
    }

}