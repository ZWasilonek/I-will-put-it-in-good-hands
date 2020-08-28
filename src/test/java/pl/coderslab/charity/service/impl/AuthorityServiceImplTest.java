package pl.coderslab.charity.service.impl;

import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.generic.AuthorityType;
import pl.coderslab.charity.repository.AuthorityRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorityServiceImplTest {

    @Mock
    private AuthorityRepository repository;
    @InjectMocks
    AuthorityServiceImpl service;

    private Authority authority;

    @BeforeEach
    void getInstance() {
        authority = new Authority();
        authority.setName(AuthorityType.ROLE_USER);
        authority.setId(1L);
    }

    @Test
    void testCreateAuthority() {
        //given
        when(repository.save(authority)).thenReturn(authority);
        //when
        Authority savedAuthority = service.create(this.authority);
        //then
        verify(repository).save(authority);
        assertThat(savedAuthority).isNotNull();
    }

    @Test
    void testFindByIdAuthority() {
        //given
        given(repository.findById(authority.getId())).willReturn(Optional.of(authority));
        //when
        Authority foundedAuthority = service.findById(1L);
        //then
        assertThat(foundedAuthority).isNotNull();
        then(repository).should().findById(anyLong());
    }

    @Test
    final void testUpdateAuthority() {
        //given
        when(repository.findById(anyLong())).thenReturn(Optional.of(authority));
        //when
        authority.setName(AuthorityType.ROLE_ADMIN);
        service.update(authority);
        //then
        verify(repository).save(authority);
        assertEquals(AuthorityType.ROLE_ADMIN, authority.getName());
    }

    @Test
    final void testFindAllAuthorities() {
        //given
        List<Authority> authorities = List.of(authority);
        given(repository.findAll()).willReturn(authorities);
        //when
        Set<Authority> foundedAuthorities = service.findAll();
        //then
        assertThat(foundedAuthorities).hasSize(1);
        then(repository).should(times(1)).findAll();
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    final void testRemoveAuthority() {
        //given
        when(repository.findById(anyLong())).thenReturn(Optional.of(authority));
        //when
        service.remove(authority);
        //then
        then(repository).should(times(1)).delete(any(Authority.class));
        verify(repository).delete(any(Authority.class));
    }

    @Test
    final void testRemoveByIdAuthority() {
        //given
        given(repository.findById(anyLong())).willReturn(Optional.of(authority));
        //when
        service.removeById(authority.getId());
        //then
        then(repository).should(times(1)).deleteById(anyLong());
    }

    @Test
    final void testFindByNameAuthorities() {
        //when
        service.findByName(authority.getName());
        service.findByName(AuthorityType.ROLE_USER);
        service.findByName(AuthorityType.ROLE_ADMIN);
        //then
        verify(repository, atMost(1)).findByName(AuthorityType.ROLE_ADMIN);
        verify(repository, times(2)).findByName(AuthorityType.ROLE_USER);
    }

    @Test
    final void testCreateAuthorityBDDThrows() {
        String errorMessage = "Cannot create item";
        RuntimeException exception = new RuntimeException(errorMessage);
        willThrow(exception).given(repository).save(any());

        assertThrows(RuntimeException.class, () -> repository.save(new Authority()));

        then(repository).should().save(any());
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    final void testFindAuthorityByIdThrows() {
        String errorMessage = "Cannot find object with id 10";
        ServiceException exception = new ServiceException(errorMessage);
        given(repository.findById(10L)).willThrow(exception);

        assertThrows(ServiceException.class, () -> service.findById(10L));

        then(repository).should().findById(10L);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    final void testFindAllAuthoritiesBDDThrows() {
        String errorMessage = "Cannot find any objects";
        RuntimeException exception = new RuntimeException(errorMessage);
        willThrow(exception).given(repository).findAll();

        assertThrows(RuntimeException.class, () -> service.findAll());

        then(repository).should().findAll();
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    final void testUpdateAuthorityBDDThrows() {
        String errorMessage = "Cannot find item to update";
        ServiceException exception = new ServiceException(errorMessage);
        willThrow(exception).given(repository).save(any());

        assertThrows(ServiceException.class, () -> repository.save(new Authority()));

        then(repository).should().save(any());
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    final void testFindByNameAuthorityDoThrow() {
        String errorMessage = "Cannot find objects";
        RuntimeException exception = new RuntimeException(errorMessage);
        doThrow(exception).when(repository).findByName(any());

        assertThrows(RuntimeException.class, () -> service.findByName(AuthorityType.ROLE_ADMIN));

        verify(repository).findByName(any());
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }


    @Test
    final void testRemoveAuthorityBDDThrows() {
        String errorMessage = "Cannot remove givenAuthority";
        RuntimeException exception = new RuntimeException(errorMessage);
        willThrow(exception).given(repository).delete(any());

        assertThrows(RuntimeException.class, () -> repository.delete(new Authority()));

        then(repository).should().delete(any());
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    final void testRemoveAuthorityByIdBDDThrows() {
        long givenID = 10L;
        String errorMessage = "Cannot remove Authority with id " + givenID;
        RuntimeException exception = new RuntimeException(errorMessage);
        willThrow(exception).given(repository).deleteById(anyLong());

        assertThrows(RuntimeException.class, () -> repository.deleteById(givenID));

        then(repository).should().deleteById(anyLong());
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

}