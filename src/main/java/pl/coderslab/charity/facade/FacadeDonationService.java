package pl.coderslab.charity.facade;

import pl.coderslab.charity.dto.CategoryDTO;
import pl.coderslab.charity.dto.DonationDTO;
import pl.coderslab.charity.dto.InstitutionDTO;
import pl.coderslab.charity.dto.UserDTO;

import java.util.Set;

public interface FacadeDonationService extends GenericServiceFacade<DonationDTO> {

    Set<CategoryDTO> getAllCategories();
    Set<InstitutionDTO> getAllInstitutions();
    UserDTO findUserByEmail(String userEmail);

}