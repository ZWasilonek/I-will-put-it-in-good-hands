package pl.coderslab.charity.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.entity.generic.AuthorityType;
import pl.coderslab.charity.entity.generic.GenericEntityID;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class Authority extends GenericEntityID {

    @Enumerated(EnumType.STRING)
    private AuthorityType name;

}