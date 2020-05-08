package pl.coderslab.charity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.coderslab.charity.entity.generic.GenericEntityID;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "categories")
@Data
public class Category extends GenericEntityID {

    private String name;
}
