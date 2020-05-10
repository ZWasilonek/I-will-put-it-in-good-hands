package pl.coderslab.charity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.coderslab.charity.entity.generic.AuthorityType;
import pl.coderslab.charity.entity.generic.GenericEntityID;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Authority extends GenericEntityID {

    @Enumerated(EnumType.STRING)
    private AuthorityType name;

    public Authority() {
    }
}
