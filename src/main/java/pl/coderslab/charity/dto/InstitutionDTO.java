package pl.coderslab.charity.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Component
public class InstitutionDTO implements Comparable<InstitutionDTO> {

    private Long id;
    private String name;
    private String description;

    @Override
    public int compareTo(InstitutionDTO o) {
        return name.compareToIgnoreCase(o.getName());
    }

}