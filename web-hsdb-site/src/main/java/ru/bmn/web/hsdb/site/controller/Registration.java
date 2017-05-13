package ru.bmn.web.hsdb.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.bmn.web.hsdb.model.entity.EntityFactory;
import ru.bmn.web.hsdb.model.entity.app.User;
import ru.bmn.web.hsdb.model.entity.app.UserRole;
import ru.bmn.web.hsdb.model.repository.app.UserRepository;
import ru.bmn.web.hsdb.site.controller.domain.UserRegistrationForm;
import ru.bmn.web.hsdb.site.security.HsdbSecurityService;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class Registration {

	@Autowired
	private	UserRepository userRepository;

	@Autowired
	private HsdbSecurityService securityService;

	@Autowired
	private EntityFactory entityFactory;

	@GetMapping("/registration")
	public ModelAndView registration(Model model) {
		model.addAttribute("userForm", new UserRegistrationForm());

		return new ModelAndView("registration");
	}

	@PostMapping("/registration")
	public ModelAndView registration(
		@Valid UserRegistrationForm userForm,
		BindingResult bindingResult,
		Model model
	) {
		Map<String, String> fieldError = new HashMap<>();
		if (bindingResult.hasErrors()) {
			fieldError = bindingResult.getFieldErrors().stream()
				.collect(
					Collectors.toMap(
						FieldError::getField,
						FieldError::getDefaultMessage)
				);

		}

		if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
			fieldError.put("passwordConfirm", "Пароль не совпадает");
		}

		if (fieldError.size() > 0) {
			model.addAttribute("fieldError", fieldError);
			return new ModelAndView("registration");
		}

		if (this.userRepository.findFirstByName(userForm.getName()) != null) {
			model.addAttribute("error", "Пользователь с таким именем ужу существует");
			return new ModelAndView("registration");
		}

		User user = this.userRepository.save(
			(User) new User()
				.setEmail(
					userForm.getEmail()
				)
				.setHearthpwnUserName(
					userForm.getHearthpwnName()
				)
				.setPasswordHash(
					DigestUtils.md5DigestAsHex(
						userForm.getPassword().getBytes()
					)
				)
				.setRegisterDate(new Date())
				.setRoles(
					new HashSet<UserRole>() {{
						add(
							(UserRole) entityFactory.getPersistEntityWithUniqueName(
								new UserRole().setName("User")
							)
						);
					}}
				)
				.setName(
					userForm.getName()
				)
		);

		this.securityService.login(
			user.getName(),
			user.getPasswordHash()
		);

		return new ModelAndView("redirect:/collection/in");
	}
}
