package ru.bmn.web.hsdb.site.controller.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.bmn.web.hsdb.model.repository.app.UserRepository;
import ru.bmn.web.hsdb.site.controller.domain.UserRegistrationForm;

@Component
public class RegistrationValidation implements Validator {
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean supports(Class<?> aClass) {
		return UserRegistrationForm.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		UserRegistrationForm userRegistration = (UserRegistrationForm) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if (userRegistration.getName().length() < 6 || userRegistration.getName().length() > 32) {
			errors.rejectValue("username", "Size.userForm.username");
		}
		if (userRepository.findFirstByName(userRegistration.getName()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (userRegistration.getPassword().length() < 8 || userRegistration.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}

		if (!userRegistration.getPasswordConfirm().equals(userRegistration.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
	}
}