package pl.coderslab.charity.service.impl;

import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class InstitutionServiceImplTest {

    @Mock
    InstitutionRepository institutionRepository;
    @InjectMocks
    InstitutionServiceImpl institutionService;
    private Institution institution;

    @BeforeEach
    void getInstance() {
        institution = new Institution();
        institution.setId(2L);
        institution.setName("Fundacja \"Dla Dzieci\"");
        institution.setDescription("Pomoc osobom znajdującym się w trudnej sytuacji życiowej.");
    }

    @Test
    final void testCreateInstitution() {
        //given
        given(institutionRepository.save(any(Institution.class))).willReturn(institution);
        //when
        Institution savedInstitution = institutionService.create(this.institution);
        //then
        then(institutionRepository).should().save(any(Institution.class));
        assertThat(savedInstitution).isNotNull();
    }

    @Test
    final void testFindInstitutionById() {
        //given
        given(institutionRepository.findById(anyLong())).willReturn(Optional.of(institution));
        //when
        Institution foundedInstitution = institutionService.findById(institution.getId());
        //then
        then(institutionRepository).should().findById(anyLong());
        assertThat(foundedInstitution).isNotNull();
        assertThat(foundedInstitution).isEqualTo(institution);
    }

    @Test
    final void testUpdateInstitution() {
        //given
        given(institutionRepository.findById(anyLong())).willReturn(Optional.ofNullable(institution));
        //when
        String givenDescription = "test description";
        institution.setDescription(givenDescription);
        institutionService.update(institution);
        //then
        then(institutionRepository).should().save(any(Institution.class));
        assertThat(institution.getDescription()).isEqualTo(givenDescription);
    }

    @Test
    final void testFindAllInstitutions() {
        //given
        List<Institution> allInstitutions = List.of(this.institution);
        given(institutionRepository.findAll()).willReturn(allInstitutions);
        //when
        Set<Institution> foundedInstitutions = institutionService.findAll();
        //then
        then(institutionRepository).should().findAll();
        assertThat(foundedInstitutions).hasSize(1);
        assertThat(allInstitutions).contains(institution);
    }

    @Test
    final void testRemoveInstitution() {
        //given
        given(institutionRepository.findById(anyLong())).willReturn(Optional.ofNullable(institution));
        //when
        boolean isInstitutionRemoved = institutionService.remove(institution);
        //then
        then(institutionRepository).should().delete(any(Institution.class));
        assertThat(isInstitutionRemoved).isEqualTo(true);
    }

    @Test
    final void testRemoveInstitutionById() {
        //given
        given(institutionRepository.findById(anyLong())).willReturn(Optional.ofNullable(institution));
        //when
        institutionService.removeById(institution.getId());
        //then
        then(institutionRepository).should().deleteById(anyLong());
    }

    @Test
    final void testFindInstitutionByName() {
        //given
        given(institutionRepository.findByName(anyString())).willReturn(institution);
        //when
        Institution foundedInstitution = institutionService.findByName(institution.getName());
        //then
        then(institutionRepository).should().findByName(anyString());
        assertThat(foundedInstitution).isNotNull();
        assertThat(foundedInstitution.getName()).isEqualTo(institution.getName());
    }

    @Test
    final void testFindInstitutionByIdBDDThrows() {
        //given
        long givenID = 10L;
        String errorMessage = "Cannot find donation by id " + givenID;
        ServiceException exception = new ServiceException(errorMessage);
        willThrow(exception).given(institutionRepository).findById(givenID);
        //when
        assertThrows(RuntimeException.class, () -> institutionService.findById(givenID));
        //then
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    final void testFindInstitutionByNameBDDThrows() {
        //given
        String givenName = "Test Foundation";
        String errorMessage = "Cannot find institution with name " + givenName;
        RuntimeException exception = new RuntimeException(errorMessage);
        willThrow(exception).given(institutionRepository).findByName(givenName);
        //when
        assertThrows(RuntimeException.class, () -> institutionService.findByName(givenName));
        then(institutionRepository).shouldHaveNoMoreInteractions();
        //then
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

}