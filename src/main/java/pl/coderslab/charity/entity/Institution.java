package pl.coderslab.charity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.coderslab.charity.entity.generic.GenericEntityID;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "institutions")
@Data
public class Institution extends GenericEntityID {

    private String name;
    private String description;

}
