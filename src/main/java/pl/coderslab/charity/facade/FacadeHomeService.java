package pl.coderslab.charity.facade;

import pl.coderslab.charity.dto.DonationDTO;
import pl.coderslab.charity.dto.InstitutionDTO;
import pl.coderslab.charity.dto.UserDTO;

import java.util.Set;

public interface FacadeHomeService {

    Set<InstitutionDTO> findAllInstitutions();
    Integer getTotalBags();
    Set<DonationDTO> findAllDonations();
    void register(UserDTO userDTO);
    UserDTO findUserByEmail(String email);

}