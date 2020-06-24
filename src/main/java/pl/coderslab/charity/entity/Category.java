package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.entity.generic.GenericEntityID;

import javax.persistence.Entity;

@Entity(name = "categories")
@Getter
@Setter
public class Category extends GenericEntityID implements Comparable<Category> {

    private String name;

    @Override
    public int compareTo(Category o) {
        return name.compareToIgnoreCase(o.getName());
    }

}