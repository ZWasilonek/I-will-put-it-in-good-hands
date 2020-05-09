package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.impl.DonationServiceImpl;
import pl.coderslab.charity.impl.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;
    private final DonationServiceImpl donationService;

    @Autowired
    public UserController(UserServiceImpl userService, DonationServiceImpl donationService) {
        this.userService = userService;
        this.donationService = donationService;
    }

    @GetMapping
    public String displayUserPanel() {
        return "user-panel";
    }

    @ModelAttribute("userSession")
    public User getUserFromSession() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String userEmail = ((UserDetails) principal).getUsername();
            return userService.findByEmail(userEmail);
        }
        return null;
    }

    @ModelAttribute("allUserBags")
    public Integer getAllUserBags() {
        Integer quantitySumFromUserId = donationService.getQuantitySumFromUserId(getUserFromSession().getId());
        if (quantitySumFromUserId != null) {
            return quantitySumFromUserId;
        }
        return 0;
    }

    @ModelAttribute("userDonations")
    public List<Donation> getAllUserDonations() {
        return donationService.getDonationsByUserId(getUserFromSession().getId());
    }

    @ModelAttribute("userInstitutions")
    public List<Institution> showUserSelectedInstitutions() {
        return getAllUserDonations().stream()
                .map(Donation::getInstitution)
                .collect(Collectors.toList());
    }
}
