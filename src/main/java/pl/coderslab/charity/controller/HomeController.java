package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.impl.DonationServiceImpl;
import pl.coderslab.charity.impl.InstitutionServiceImpl;
import pl.coderslab.charity.impl.SecurityServiceImpl;
import pl.coderslab.charity.impl.UserServiceImpl;
import pl.coderslab.charity.validation.LoginValidation;
import pl.coderslab.charity.validation.RegistrationValidation;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    private final InstitutionServiceImpl institutionService;
    private final DonationServiceImpl donationService;
    private final UserServiceImpl userService;
    private final HttpSession session;
    private final RegistrationValidation registrationValidation;
    private final LoginValidation loginValidation;
    private final SecurityServiceImpl securityService;

    @Autowired
    public HomeController(InstitutionServiceImpl institutionService, DonationServiceImpl donationService, UserServiceImpl userService, HttpSession session, RegistrationValidation registrationValidation, LoginValidation loginValidation, SecurityServiceImpl securityService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.session = session;
        this.registrationValidation = registrationValidation;
        this.loginValidation = loginValidation;
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
//        if (bindingResult.hasErrors()) return "register";
        userService.saveUserDTO(userDTO);
        if (!getSession(userDTO)) return "register";
        securityService.autoLogin(userDTO.getEmail(), userDTO.getConfirmPassword());
        return "redirect:/donation";
    }


    //----------------------SIGN IN------------------------
    @GetMapping("/login")
    public String displayLoginPage(Model model) {
        model.addAttribute("loginUser", new UserDTO());
        return "login";
    }

//    @PostMapping("/login")
//    public String getUserFromLoginForm(@Valid @ModelAttribute("loginUser") UserDTO userDTO,
//                                       BindingResult bindingResult) {
//        loginValidation.validate(userDTO, bindingResult);
////        if (bindingResult.hasErrors()) return "login";
//        try {
//            securityService.autoLogin(userDTO.getEmail(), userDTO.getPassword());
//            if (!getSession(userDTO)) return "login";
//        } catch (UsernameNotFoundException e) {
//            return "login";
//        }
//        return "redirect:/donation";
//    }
//
//    @GetMapping("/login")
//    public String login(HttpServletRequest request, Model model) {
//        HttpSession session = request.getSession(false);
//        String errorMessage = null;
//        if (session != null) {
//            AuthenticationException ex = (AuthenticationException) session
//                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//            if (ex != null) {
//                errorMessage = ex.getMessage();
//            }
//        }
//        model.addAttribute("errorMessage", errorMessage);
//        return "login";
//    }
//    @ModelAttribute("sessionUser")
//    public User getUserFromSession() {
//        Long userId = (Long) session.getAttribute("userId");
//        return userService.findById(userId);
//    }

    public boolean getSession(UserDTO userDTO) {
        User user = userService.findByEmail(userDTO.getEmail());
        if (user != null) {
            Long id = user.getId();
            if (id != null) {
                session.setAttribute("userId", id);
                session.setMaxInactiveInterval(7200);
                return true;
            }
        }
        return false;
    }
}
