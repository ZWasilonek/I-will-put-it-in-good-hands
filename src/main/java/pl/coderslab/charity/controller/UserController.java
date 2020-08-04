package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dto.DonationDTO;
import pl.coderslab.charity.dto.InstitutionDTO;
import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.facade.FacadeUserService;
import pl.coderslab.charity.validation.RegistrationValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final FacadeUserService facadeService;
    private final RegistrationValidation validation;

    @Autowired
    public UserController(FacadeUserService facadeUserService, RegistrationValidation validation) {
        this.facadeService = facadeUserService;
        this.validation = validation;
    }

    //------------------USER DONATIONS---------------------
    @GetMapping("/donations")
    public String displayUserDonations() {
        return "user-donations";
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

    //------------------USER PROFILE---------------------
    @GetMapping("/edit")
    public String showUserForm(Model model) {
        UserDTO userFromSession = getUserFromSession();
        userFromSession.setPassword("");
        model.addAttribute("userFromSession", userFromSession);
        return "user-panel";
    }

    @PostMapping("/edit")
    public String userEditForm(@Valid @ModelAttribute("userFromSession") UserDTO userSession,
                               BindingResult bindingResult, Model model) {
        userSession.setId(getUserFromSession().getId());
        validation.validate(userSession, bindingResult);
        if (!bindingResult.hasErrors()) {
            facadeService.update(userSession);
            model.addAttribute("userSaved", true);
        }
        return "user-panel";
    }

    @GetMapping("/remove")
    public String removeUser(HttpServletRequest request) {
        boolean isUserDisabled = facadeService.deactivateTheAccountByUserId(getUserFromSession().getId());
        if (isUserDisabled) {
            HttpSession session= request.getSession(false);
            SecurityContextHolder.clearContext();
            if(session != null) {
                session.invalidate();
            }
            return "redirect:/logout";
        }
        return "redirect:error";
    }

}