package pl.coderslab.charity.facade;

import pl.coderslab.charity.dto.DonationDTO;
import pl.coderslab.charity.dto.UserDTO;
import java.util.Set;

public interface FacadeUserService extends GenericServiceFacade<UserDTO> {

    UserDTO findUserByEmail(String userEmail);
    Integer getBagsSumByUserId(Long id);
    Set<DonationDTO> getAllDonationsByUserId(Long id);
    boolean deactivateTheAccountByUserId(Long userId);

}