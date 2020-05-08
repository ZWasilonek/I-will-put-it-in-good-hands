package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.impl.DonationServiceImpl;
import pl.coderslab.charity.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;
    private final DonationServiceImpl donationService;
    private final HttpSession session;

    @Autowired
    public UserController(UserServiceImpl userService, DonationServiceImpl donationService, HttpSession session) {
        this.userService = userService;
        this.donationService = donationService;
        this.session = session;
    }

    @GetMapping
    public String displayUserPanel() {
        return "user-panel";
    }

    @ModelAttribute("sessionUser")
    public User getUserFromSession() {
        return userService.findById(userSessionId());
    }

    @ModelAttribute("allUserBags")
    public Integer getAllUserBags() {
        Integer quantitySumFromUserId = donationService.getQuantitySumFromUserId(userSessionId());
        if (quantitySumFromUserId != null) {
            return quantitySumFromUserId;
        }
        return 0;
    }

    @ModelAttribute("userDonations")
    public List<Donation> getAllUserDonations() {
        return donationService.getDonationsByUserId(userSessionId());
    }

    @ModelAttribute("userInstitutions")
    public List<Institution> showUserSelectedInstitutions() {
        return getAllUserDonations().stream()
                .map(Donation::getInstitution)
                .collect(Collectors.toList());
    }

    public Long userSessionId() {
        return (Long) session.getAttribute("userId");
    }
}
