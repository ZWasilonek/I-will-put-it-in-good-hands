package pl.coderslab.charity.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.coderslab.charity.dto.*;
import pl.coderslab.charity.facade.FacadeDonationService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DonationControllerTest {

    @Mock
    FacadeDonationService donationService;
    @Mock
    Model model;
    @Mock
    BindingResult bindingResult;
    @InjectMocks
    DonationController donationController;
    @InjectMocks
    BCryptPasswordEncoder passwordEncoder;

    private UserDTO userDTO;
    private DonationDTO donationDTO;
    private InstitutionDTO institutionDTO;

    @BeforeEach
    void getUserInstance() {
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
    final void testDisplayForm() {
        //when
        String formPage = donationController.displayForm(model);
        //then
        assertThat(formPage).isEqualToIgnoringCase("form");
    }

}