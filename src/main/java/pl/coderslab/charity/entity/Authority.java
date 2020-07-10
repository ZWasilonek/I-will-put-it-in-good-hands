package pl.coderslab.charity.entity;

import lombok.*;
import pl.coderslab.charity.entity.generic.AuthorityType;
import pl.coderslab.charity.entity.generic.GenericEntityID;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authority extends GenericEntityID {

    @Enumerated(EnumType.STRING)
    private AuthorityType name;

}