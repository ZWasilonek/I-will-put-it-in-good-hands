package pl.coderslab.charity.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.coderslab.charity.dto.*;
import pl.coderslab.charity.facade.FacadeHomeService;
import pl.coderslab.charity.validation.RegistrationValidation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @Mock
    FacadeHomeService homeService;
    @Mock
    BindingResult bindingResult;
    @Mock
    Model model;
    @Mock
    RegistrationValidation validator;
    @InjectMocks
    BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    HomeController homeController;
    private DonationDTO donationDTO;
    private UserDTO userDTO;
    private InstitutionDTO institutionDTO;

    @BeforeEach
    void getDonationInstance() {
        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("Jan");
        userDTO.setLastName("Kowalski");
        userDTO.setEmail("jan.kowalski@gmail.com");
        userDTO.setPassword(passwordEncoder.encode("password"));
        userDTO.setEnabled(true);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Zabawki");

        institutionDTO = new InstitutionDTO();
        institutionDTO.setId(2L);
        institutionDTO.setName("Fundacja \"Dla Dzieci\"");
        institutionDTO.setDescription("Pomoc osobom znajdującym się w trudnej sytuacji życiowej.");

        ShippingAddressDTO shippingAddressDTO = new ShippingAddressDTO();
        shippingAddressDTO.setId(3L);
        shippingAddressDTO.setCity("Warszawa");
        shippingAddressDTO.setPhoneNumber("820-654-946");
        shippingAddressDTO.setPickUpComment("Brak");
        shippingAddressDTO.setStreet("Kwiatowa");
        shippingAddressDTO.setZipCode("00-876");
        shippingAddressDTO.setPickUpDate(LocalDate.now());
        shippingAddressDTO.setPickUpTime(LocalTime.now());

        donationDTO = new DonationDTO();
        donationDTO.setId(1L);
        donationDTO.setBagsQuantity(2);
        donationDTO.setCategories(Set.of(categoryDTO));
        donationDTO.setInstitution(institutionDTO);
        donationDTO.setShippingAddress(shippingAddressDTO);
        donationDTO.setUserId(userDTO.getId());
    }

    @Test
    final void testHomeAction() {
        //when
        String homePage = homeController.homeAction();
        //then
        assertThat(homePage).isEqualToIgnoringCase("index");
    }

    @Test
    final void testFindAllInstitutions() {
        //given
        Set<InstitutionDTO> institutions = Set.of(institutionDTO);
        given(homeService.findAllInstitutions()).willReturn(institutions);
        //when
        Set<InstitutionDTO> allInstitutions = homeController.findAllInstitutions();
        //then
        then(homeService).should().findAllInstitutions();
        assertThat(allInstitutions).hasSize(1);
        assertThat(allInstitutions).contains(institutionDTO);
    }

    @Test
    final void testGetTotalBagsNumberFromAllDonations() {
        //given
        Integer totalBags = donationDTO.getBagsQuantity();
        given(homeService.getTotalBags()).willReturn(totalBags);
        //when
        Integer totalBagsNumberFromAllDonations = homeController.getTotalBagsNumberFromAllDonations();
        //then
        then(homeService).should().getTotalBags();
        assertThat(totalBagsNumberFromAllDonations).isEqualTo(totalBags);
    }

    @Test
    final void testGetAllDonations() {
        //given
        Set<DonationDTO> donations = Set.of(this.donationDTO);
        given(homeService.findAllDonations()).willReturn(donations);
        //when
        Integer allDonations = homeController.getAllDonations();
        //then
        then(homeService).should().findAllDonations();
        assertThat(allDonations).isEqualTo(donations.size());
    }

    @Test
    void displayRegistration() {
        //when
        String registrationPage = homeController.displayRegistration(model);
        //then
        assertThat(registrationPage).isEqualTo("register");
    }

    @Test
    final void testGetRegisteredUserWithNoErrors() {
        //given
        validator.validate(userDTO, bindingResult);
        given(bindingResult.hasErrors()).willReturn(false);
        //when
        String registrationPage = homeController.getRegisteredUser(userDTO, bindingResult);
        //then
        assertThat(registrationPage).isEqualToIgnoringCase("redirect:/donation");
    }

    @Test
    final void testGetRegisteredUserWithErrors() {
        //given
        validator.validate(userDTO, bindingResult);
        given(bindingResult.hasErrors()).willReturn(true);
        //when
        String registrationPage = homeController.getRegisteredUser(userDTO, bindingResult);
        //then
        assertThat(registrationPage).isEqualToIgnoringCase("register");
    }

    @Test
    final void testDisplayLoginPageWithNoErrors() {
        //given
        model.addAttribute("loginUser", new UserDTO());
        //when
        String loginPage = homeController.displayLoginPage(model);
        //then
        assertThat(loginPage).isEqualToIgnoringCase("login");
    }

    @Test
    void error403() {
        //when
        String errorPage = homeController.error403();
        //then
        assertThat(errorPage).isEqualToIgnoringCase("redirect:error");
    }

}