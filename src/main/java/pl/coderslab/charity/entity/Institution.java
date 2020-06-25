package pl.coderslab.charity.entity;

import lombok.*;
import pl.coderslab.charity.entity.generic.GenericEntityID;
import javax.persistence.Entity;

@Entity(name = "institutions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Institution extends GenericEntityID implements Comparable<Institution> {

    private String name;
    private String description;

    @Override
    public int compareTo(Institution o) {
        return name.compareToIgnoreCase(o.getName());
    }

}