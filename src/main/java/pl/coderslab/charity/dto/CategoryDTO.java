package pl.coderslab.charity.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@EqualsAndHashCode
@Component
public class CategoryDTO implements Comparable<CategoryDTO> {

    private Long id;
    private String name;

    @Override
    public int compareTo(CategoryDTO o) {
        return name.compareToIgnoreCase(o.getName());
    }

}