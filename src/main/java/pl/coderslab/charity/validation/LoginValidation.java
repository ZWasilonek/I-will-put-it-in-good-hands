package pl.coderslab.charity.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.impl.UserServiceImpl;

import static org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.*;

@Component
public class LoginValidation implements Validator {

    private final UserServiceImpl userService;

    @Autowired
    public LoginValidation(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationValidation.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotBlank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotBlank");

        String enteredEmail = userDTO.getEmail();
        String enteredPassword = userDTO.getPassword();

        User userFromDatabase = userService.findByEmail(enteredEmail);
        if (userFromDatabase != null && !enteredEmail.equals("")) {
            if (!enteredPassword.equals("")) {
                if (!new BCryptPasswordEncoder().matches(enteredPassword, userFromDatabase.getPassword())) {
                    errors.rejectValue("password", "NotExists.userForm.Password");
                }
            }
        } else if (!enteredEmail.equals("")) errors.rejectValue("email", "NotExists.userForm.Email");

    }
}
