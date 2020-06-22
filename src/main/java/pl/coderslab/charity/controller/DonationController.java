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
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.impl.CategoryServiceImpl;
import pl.coderslab.charity.impl.DonationServiceImpl;
import pl.coderslab.charity.impl.InstitutionServiceImpl;
import pl.coderslab.charity.impl.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private final CategoryServiceImpl categoryService;
    private final InstitutionServiceImpl institutionService;
    private final DonationServiceImpl donationService;
    private final UserServiceImpl userService;

    @Autowired
    public DonationController(CategoryServiceImpl categoryService, InstitutionServiceImpl institutionService, DonationServiceImpl donationService, UserServiceImpl userService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
    }

    @GetMapping
    public String displayForm(Model model) {
        model.addAttribute("donationForm", new Donation());
        return "form";
    }

    @PostMapping
    public String donationForm(@Valid @ModelAttribute("donationForm") Donation donationForm,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        if (donationForm != null) {
            getUserFromSession().getDonations().add(donationForm);
            donationService.create(donationForm);
        }
        return "redirect:/donation/confirm";
    }

    @GetMapping("/confirm")
    public String displayConfirmation() {
        return "form-confirmation";
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionService.findAll();
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

    @GetMapping("/403")
    public String error403() {
        return "redirect:error";
    }

}