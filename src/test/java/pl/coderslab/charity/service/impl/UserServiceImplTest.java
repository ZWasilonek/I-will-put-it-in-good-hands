package pl.coderslab.charity.service.impl;

import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.charity.entity.Authority;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.entity.generic.AuthorityType;
import pl.coderslab.charity.repository.AuthorityRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @Mock
    AuthorityRepository authorityRepository;
    @InjectMocks
    UserServiceImpl userService;
    @InjectMocks
    BCryptPasswordEncoder passwordEncoder;
    @Captor
    ArgumentCaptor<String> emailCaptor;

    private User user;

    @BeforeEach
    void getInstance() {
        user = new User();
        user.setId(2L);
        user.setFirstName("Jan");
        user.setLastName("Kowalski");
        user.setEmail("jan.kowalski@gmail.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setEnabled(true);
    }

    @Test
    final void testCreateUser() {
        given(userRepository.save(any(User.class))).willReturn(user);

        User savedUser = userService.create(this.user);

        then(userRepository).should().save(any(User.class));
        assertThat(savedUser).isEqualTo(user);
    }

    @Test
    final void testFindUserById() {
        final ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        given(userRepository.findById(idCaptor.capture())).willReturn(Optional.ofNullable(user));

        User foundedUser = userService.findById(user.getId());

        then(userRepository).should().findById(anyLong());
        assertThat(idCaptor.getValue()).isEqualTo(foundedUser.getId());
    }

    @Test
    final void testUpdateUser() {
        given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
        String givenEmail = "new@gmail.com";
        user.setEmail(givenEmail);

        userService.update(user);

        then(userRepository).should().save(any(User.class));
        assertThat(user.getEmail()).isEqualTo(givenEmail);
    }

    @Test
    final void testFindAllUsers() {
        given(userRepository.findAll()).willReturn(List.of(user));

        Set<User> allUsers = userService.findAll();

        then(userRepository).should().findAll();
        assertThat(allUsers).hasSize(1);
    }

    @Test
    final void testRemoveUser() {
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(user));

        boolean isUserRemoved = userService.remove(user);

        then(userRepository).should().delete(any(User.class));
        assertThat(isUserRemoved).isEqualTo(true);
    }

    @Test
    final void testRemoveUserById() {
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(user));

        boolean isUserRemoved = userService.removeById(user.getId());

        then(userRepository).should().deleteById(anyLong());
        assertThat(isUserRemoved).isEqualTo(true);
    }

    @Test
    final void testFindUserByEmail() {
        given(userRepository.findByEmail(emailCaptor.capture())).willReturn(user);

        User founded = userService.findByEmail(user.getEmail());

        then(userRepository).should().findByEmail(anyString());
        assertThat(emailCaptor.getValue()).isEqualTo(founded.getEmail());
    }

    @Test
    final void testDisableUser() {
        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(user));

        boolean isUserDisabled = userService.disableUser(user.getId());

        then(userRepository).should().save(user);
        assertThat(isUserDisabled).isEqualTo(true);
    }

    @Test
    final void testSetRoleUser() {
        //given
        User admin = new User();
        admin.setId(1L);
        Authority roleAdmin = authorityRepository.findByName(AuthorityType.ROLE_ADMIN);
        Authority roleUser = authorityRepository.findByName(AuthorityType.ROLE_USER);
        //when
        userService.setRole(this.user);
        //then
        then(userRepository).should().save(any(User.class));
        assertThat(user.getAuthorities()).hasSize(1);
    }

    @Test
    final void testCreateUserBDDThrows() {
        String errorMessage = "Cannot create user";
        RuntimeException exception = new RuntimeException(errorMessage);
        willThrow(exception).given(userRepository).save(any(User.class));

        assertThrows(RuntimeException.class, () -> userService.create(new User()));

        then(userRepository).should().save(any(User.class));
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    @Test
    final void testFindUserByIdBDDThrows() {
        long id = 10L;
        String errorMessage = "Cannot find item with id " + id;
        ServiceException exception = new ServiceException(errorMessage);
        willThrow(exception).given(userRepository).findById(id);

        assertThrows(ServiceException.class, () -> userService.findById(id));

        then(userRepository).should().findById(id);
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

}