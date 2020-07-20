package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dto.InstitutionDTO;
import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.facade.FacadeHomeService;
import pl.coderslab.charity.validation.RegistrationValidation;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class HomeController {

    private final FacadeHomeService facadeService;
    private final RegistrationValidation validator;

    @Autowired
    public HomeController(FacadeHomeService facadeHomeService, RegistrationValidation validator) {
        this.facadeService = facadeHomeService;
        this.validator = validator;
    }

    @RequestMapping("/")
    public String homeAction(){
        return "index";
    }

    @ModelAttribute("institutions")
    public Set<InstitutionDTO> findAllInstitutions() {
        return facadeService.findAllInstitutions();
    }

    @ModelAttribute("allBags")
    public Integer getTotalBagsNumberFromAllDonations() {
        return facadeService.getTotalBags();
    }

    @ModelAttribute("allDonations")
    public Integer getAllDonations() {
        return facadeService.findAllDonations().size();
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

    //------------------REGISTRATION---------------------
    @GetMapping("/register")
    public String displayRegistration(Model model) {
        model.addAttribute("registeredUser", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String getRegisteredUser(@Valid @ModelAttribute("registeredUser") UserDTO userDTO,
                                    BindingResult bindingResult) {
        validator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) return "register";
        facadeService.register(userDTO, bindingResult);
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