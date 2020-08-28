package pl.coderslab.charity.service.impl;

import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.charity.entity.*;
import pl.coderslab.charity.repository.DonationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DonationServiceImplTest {

    @Mock
    private DonationRepository donationRepository;
    @InjectMocks
    BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    DonationServiceImpl donationService;

    private Donation donation;

    @BeforeEach
    void getInstance() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Jan");
        user.setLastName("Kowalski");
        user.setEmail("jan.kowalski@gmail.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setEnabled(true);

        Category category = new Category();
        category.setId(1L);
        category.setName("Zabawki");

        Institution institution = new Institution();
        institution.setId(2L);
        institution.setName("Fundacja \"Dla Dzieci\"");
        institution.setDescription("Pomoc osobom znajdującym się w trudnej sytuacji życiowej.");

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setId(3L);
        shippingAddress.setCity("Warszawa");
        shippingAddress.setPhoneNumber("820-654-946");
        shippingAddress.setPickUpComment("Brak");
        shippingAddress.setStreet("Kwiatowa");
        shippingAddress.setZipCode("00-876");
        shippingAddress.setPickUpDate(LocalDate.now());
        shippingAddress.setPickUpTime(LocalTime.now());

        donation = new Donation();
        donation.setId(1L);
        donation.setBagsQuantity(2);
        donation.setCategories(Set.of(category));
        donation.setInstitution(institution);
        donation.setShippingAddress(shippingAddress);
        donation.setUser(user);
    }

    @Test
    final void testGetTotalBagsQuantity() {
        //given
        given(donationRepository.getTotalBags()).willReturn(donation.getBagsQuantity());
        //when
        Integer totalBagsQuantity = donationService.getTotalBagsQuantity();
        //then
        then(donationRepository).should(times(1)).getTotalBags();
        assertThat(totalBagsQuantity).isEqualTo(2);
    }

    @Test
    final void testGetQuantitySumFromUserId() {
        //given
        given(donationRepository.getQuantitySumFromUserId(anyLong())).willReturn(donation.getBagsQuantity());
        //when
        Integer userBags = donationService.getQuantitySumFromUserId(donation.getUser().getId());
        //then
        then(donationRepository).should(times(1)).getQuantitySumFromUserId(anyLong());
        assertThat(userBags).isEqualTo(2);
    }

    @Test
    final void testGetDonationsByUserId() {
        //given
        Set<Donation> donations = Set.of(donation);
        given(donationRepository.getDonationsByUserId(anyLong())).willReturn(donations);
        //when
        donationService.getDonationsByUserId(donation.getUser().getId());
        //then
        then(donationRepository).should(times(1)).getDonationsByUserId(anyLong());
        assertThat(donations).hasSize(1);
    }

    @Test
    final void testCreateDonation() {
        //given
        when(donationRepository.save(any(Donation.class))).thenReturn(donation);
        //when
        Donation savedDonation = donationService.create(this.donation);
        //then
        then(donationRepository).should().save(any(Donation.class));
        assertThat(savedDonation).isNotNull();
    }

    @Test
    final void testFindByIdDonation() {
        //given
        given(donationRepository.findById(anyLong())).willReturn(Optional.of(donation));
        //when
        Donation foundedDonation = donationService.findById(donation.getId());
        //then
        then(donationRepository).should().findById(anyLong());
        assertThat(foundedDonation).isNotNull();
        assertThat(foundedDonation).isEqualTo(donation);
    }

    @Test
    final void testUpdateDonation() {
        //given
        given(donationRepository.findById(anyLong())).willReturn(Optional.ofNullable(donation));
        donation.setBagsQuantity(20);
        //when
        donationService.update(donation);
        //then
        then(donationRepository).should().save(any(Donation.class));
        assertThat(20).isEqualTo(donation.getBagsQuantity());
    }

    @Test
    final void testFindAllDonations() {
        //given
        when(donationRepository.findAll()).thenReturn(List.of(donation));
        //when
        Set<Donation> allDonations = donationService.findAll();
        //then
        then(donationRepository).should().findAll();
        assertThat(allDonations).hasSize(1);
    }

    @Test
    final void testRemoveDonations() {
        //given
        given(donationRepository.findById(anyLong())).willReturn(Optional.ofNullable(donation));
        //when
        boolean isDonationRemoved = donationService.remove(donation);
        //then
        then(donationRepository).should().delete(any(Donation.class));
        assertThat(isDonationRemoved).isEqualTo(true);
    }

    @Test
    final void testRemoveDonationById() {
        //given
        when(donationRepository.findById(anyLong())).thenReturn(Optional.ofNullable(donation));
        //then
        boolean isDonationRemoved = donationService.removeById(donation.getId());
        //then
        then(donationRepository).should().deleteById(anyLong());
        assertThat(isDonationRemoved).isEqualTo(true);
    }

    @Test
    final void testFindDonationByIdBDDThrows() {
        long givenID = 10L;
        String errorMessage = "Cannot find donation by id " + givenID;
        ServiceException exception = new ServiceException(errorMessage);
        willThrow(exception).given(donationRepository).findById(givenID);

        assertThrows(ServiceException.class, () -> donationService.findById(givenID));
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

}