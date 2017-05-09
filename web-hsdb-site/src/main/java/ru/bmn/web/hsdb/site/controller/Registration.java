package ru.bmn.web.hsdb.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.bmn.web.hsdb.model.entity.app.User;
import ru.bmn.web.hsdb.model.entity.app.UserRole;
import ru.bmn.web.hsdb.model.repository.app.UserRepository;
import ru.bmn.web.hsdb.site.controller.domain.UserRegistrationForm;
import ru.bmn.web.hsdb.site.controller.validation.RegistrationValidation;
import ru.bmn.web.hsdb.site.security.HsdbSecurityService;

import java.util.HashSet;

@Controller
public class Registration {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private HsdbSecurityService securityService;

	@Autowired
	private RegistrationValidation registrationValidation;

	@GetMapping("/registration")
	public ModelAndView registration(Model model) {
		model.addAttribute("userForm", new UserRegistrationForm());

//		return new ModelAndView("registration.html");
		return new ModelAndView("registration");
	}

	@PostMapping("/registration")
	public String registration(
		@ModelAttribute("userForm") UserRegistrationForm userForm,
		BindingResult bindingResult,
		Model model
	) {
		this.registrationValidation.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
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
				.setRoles(
					new HashSet<UserRole>() {{
						add(new UserRole().setName("User"));
					}}
				)
				.setName(
					userForm.getName()
				)
		);

		securityService.autologin(user.getName(), user.getPasswordHash());

		return "redirect:/collection/in";
	}
}
