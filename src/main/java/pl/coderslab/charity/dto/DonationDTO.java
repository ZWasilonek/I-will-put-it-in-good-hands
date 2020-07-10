package pl.coderslab.charity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
@AllArgsConstructor
@Component
public class DonationDTO {

    private Long id;
    private Integer bagsQuantity;
    private Set<CategoryDTO> categories;
    private InstitutionDTO institution;
    private ShippingAddressDTO shippingAddress;
    private Long userId;

    public DonationDTO() {
        categories = new TreeSet<>();
    }

}