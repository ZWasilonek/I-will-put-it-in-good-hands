package pl.coderslab.charity.service.impl;

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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

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

}