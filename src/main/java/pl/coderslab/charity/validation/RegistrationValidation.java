package pl.coderslab.charity.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.coderslab.charity.dto.UserDTO;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;

@Component
public class RegistrationValidation implements Validator {

    private final UserService userService;

    @Autowired
    public RegistrationValidation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationValidation.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotBlank");
        String enteredFirstName = userDTO.getFirstName();
        if (!enteredFirstName.equals("")) {
            if (!enteredFirstName.matches("^[\\p{L}]{1,29}$")) {
                errors.rejectValue("firstName", "Pattern.userForm.FirstName");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotBlank");
        String enteredLastName = userDTO.getLastName();
        if (!enteredLastName.equals("")) {
            if (!enteredLastName.matches("^[\\p{L}]{1,29}$")) {
                errors.rejectValue("lastName", "Pattern.userForm.LastName");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotBlank");
        String enteredEmail = userDTO.getEmail();
        String enteredPassword = userDTO.getPassword();
        User userFromDatabase = userService.findByEmail(enteredEmail);
        if (userFromDatabase != null) {
            if (userFromDatabase.getEmail().equals(enteredEmail)
                    && !userFromDatabase.getId().equals(userDTO.getId())
                    && userDTO.isEnabled()
                    ||
                    userFromDatabase.getEmail().equals(enteredEmail)
                    && userDTO.getId() == null
                    && userFromDatabase.isEnabled()) {
                errors.rejectValue("email", "Duplicated.userForm.Email");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotBlank");
        if (!enteredPassword.equals("") && !enteredPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$")) {
            errors.rejectValue("password", "Pattern.userForm.Password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotBlank");
        if (!enteredPassword.equals(userDTO.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "PasswordConfirmation.userForm.Password");
        }

    }

}