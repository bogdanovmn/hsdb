package com.github.bogdanovmn.hsdb.webapp;

import com.github.bogdanovmn.hsdb.model.EntityFactory;
import com.github.bogdanovmn.hsdb.model.User;
import com.github.bogdanovmn.hsdb.model.UserRepository;
import com.github.bogdanovmn.hsdb.model.UserRole;
import com.github.bogdanovmn.hsdb.webapp.config.security.HsdbSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;

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
		FormErrors formErrors = new FormErrors(bindingResult);

		if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
			formErrors.add("passwordConfirm", "Пароль не совпадает");
		}
		else if (this.userRepository.findFirstByName(userForm.getName()) != null) {
			formErrors.addCustom("Пользователь с таким именем уже существует");
		}

		if (formErrors.isNotEmpty()) {
			model.addAllAttributes(formErrors.getModel());
			return new ModelAndView("registration", model.asMap());
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
