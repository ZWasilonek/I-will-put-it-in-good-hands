package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dto.DonationDTO;
import pl.coderslab.charity.dto.InstitutionDTO;
import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.facade.FacadeUserService;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final FacadeUserService facadeService;

    @Autowired
    public UserController(FacadeUserService facadeUserService) {
        this.facadeService = facadeUserService;
    }

    @GetMapping
    public String displayUserPanel() {
        return "user-panel";
    }

    @ModelAttribute("userSession")
    public UserDTO getUserFromSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String userEmail = ((UserDetails) principal).getUsername();
            return facadeService.findUserByEmail(userEmail);
        }
        return null;
    }

    @ModelAttribute("allUserBags")
    public Integer getAllUserBags() {
        Integer quantitySumFromUserId = facadeService.getBagsSumByUserId(getUserFromSession().getId());
        return Objects.requireNonNullElse(quantitySumFromUserId, 0);
    }

    @ModelAttribute("userDonations")
    public Set<DonationDTO> getAllUserDonations() {
        return facadeService.getAllDonationsByUserId(getUserFromSession().getId());
    }

    @ModelAttribute("userInstitutions")
    public Set<InstitutionDTO> showUserSelectedInstitutions() {
        return getAllUserDonations().stream()
                .map(DonationDTO::getInstitution)
                .collect(Collectors.toSet());
    }

}