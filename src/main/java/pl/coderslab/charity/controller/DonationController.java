package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.dto.*;
import pl.coderslab.charity.facade.FacadeDonationService;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private final FacadeDonationService facadeService;

    @Autowired
    public DonationController(FacadeDonationService donationService) {
        this.facadeService = donationService;
    }

    @GetMapping
    public String displayForm(Model model) {
        model.addAttribute("donationForm", new DonationDTO());
        return "form";
    }

    @PostMapping
    public String donationForm(@Valid @ModelAttribute("donationForm") DonationDTO donationForm,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        if (donationForm != null) {
            getUserFromSession().getDonations().add(donationForm);
            donationForm.getShippingAddress().setAddressOwner(getUserFromSession());
            facadeService.create(donationForm);
        }
        return "redirect:/donation/confirm";
    }

    @GetMapping("/confirm")
    public String displayConfirmation() {
        return "form-confirmation";
    }

    @ModelAttribute("categories")
    public Set<CategoryDTO> getAllCategories() {
        return facadeService.getAllCategories();
    }

    @ModelAttribute("institutions")
    public Set<InstitutionDTO> getAllInstitutions() {
        return facadeService.getAllInstitutions();
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

    @GetMapping("/403")
    public String error403() {
        return "redirect:error";
    }

}