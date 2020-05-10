package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.impl.DonationServiceImpl;
import pl.coderslab.charity.impl.InstitutionServiceImpl;
import pl.coderslab.charity.impl.SecurityServiceImpl;
import pl.coderslab.charity.impl.UserServiceImpl;
import pl.coderslab.charity.validation.RegistrationValidation;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    private final InstitutionServiceImpl institutionService;
    private final DonationServiceImpl donationService;
    private final UserServiceImpl userService;
    private final RegistrationValidation registrationValidation;
    private final SecurityServiceImpl securityService;

    @Autowired
    public HomeController(InstitutionServiceImpl institutionService, DonationServiceImpl donationService, UserServiceImpl userService, RegistrationValidation registrationValidation,SecurityServiceImpl securityService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.registrationValidation = registrationValidation;
        this.securityService = securityService;
    }

    @RequestMapping("/")
    public String homeAction(){
        return "index";
    }

    @ModelAttribute("institutions")
    public List<Institution> findAllInstitutions() {
        List<Institution> a = institutionService.findAll();
        return institutionService.findAll();
    }

    @ModelAttribute("allBags")
    public Integer getQuantitySumFromAllDonations() {
        return donationService.getQuantitySumFromAll();
    }

    @ModelAttribute("allDonations")
    public Integer getAllDonations() {
        return donationService.findAll().size();
    }


    //------------------REGISTRATION---------------------
    @GetMapping("/register")
    public String displayRegistration(Model model) {
        model.addAttribute("registeredUser", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String getRegisteredUser(@Valid @ModelAttribute("registeredUser") UserDTO userDTO,
                                    BindingResult bindingResult) {
        registrationValidation.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) return "register";
        userService.saveUserDTO(userDTO);
        securityService.autoLogin(userDTO.getEmail(), userDTO.getConfirmPassword());
        return "redirect:/donation";
    }


    //----------------------SIGN IN------------------------
    @GetMapping("/login")
    public String displayLoginPage(Model model) {
        model.addAttribute("loginUser", new UserDTO());
        return "login";
    }


    @GetMapping("/403")
    public String error403() {
        return "redirect:error";
    }
}
