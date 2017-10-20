package com.github.bogdanovmn.hsdb.webapp;

import com.github.bogdanovmn.hsdb.model.User;
import com.github.bogdanovmn.hsdb.model.UserRepository;
import com.github.bogdanovmn.hsdb.webapp.config.security.HsdbSecurityService;
import com.github.bogdanovmn.hsdb.webapp.config.security.Md5PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/user/settings")
public class UserSettings {

	@Autowired
	private	UserRepository userRepository;

	@Autowired
	private HsdbSecurityService securityService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@GetMapping
	public ModelAndView form(
		Model model,
		@RequestHeader(name = "referer", required = false) String referer
	) {
		User user = this.securityService.getLoggedInUser();

		model.addAttribute("referer", referer);
		model.addAttribute("userName", user.getName());
		model.addAttribute("userEmail", user.getEmail());
		model.addAttribute("userRegistrationDate", user.getRegisterDate());
		model.addAttribute(
			"userSettingsForm",
			new UserSettingsForm().setHearthpwnUserName(
				user.getHearthpwnUserName()
			)
		);
		return new ModelAndView("user_settings");
	}

	@PostMapping
	public ModelAndView update(
		@Valid UserSettingsForm form,
		BindingResult bindingResult,
		Model model,
		@RequestHeader(name = "referer", required = false) String referer
	) {
		FormErrors formErrors = new FormErrors(bindingResult);

		User user = this.securityService.getLoggedInUser();

		if (form.getNewPassword() != null) {
			String currentPassword = form.getCurrentPassword();
			if (currentPassword == null) {
				formErrors.add("currentPassword", "Необходимо указать");
			}
			else if (form.getNewPassword() == null) {
				formErrors.add("newPassword", "Необходимо указать");
			}
			else if (!form.getNewPassword().equals(form.getNewPasswordConfirm())) {
				formErrors.add("newPasswordConfirm", "Повтор нового пароля не совпадает");
			}
			else {
				Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
				if (!passwordEncoder.encode(currentPassword).equals(user.getPasswordHash())) {
					formErrors.add("currentPassword", "Пароль введен неправильно");
				}
				else {
					user.setPasswordHash(
						passwordEncoder.encode(
							form.getNewPassword()
						)
					);
				}
			}
		}

		if (formErrors.isNotEmpty()) {
			model.addAllAttributes(formErrors.getModel());
			model.addAttribute("referer", referer);
			model.addAttribute("userName", user.getName());
			return new ModelAndView("user_settings");
		}

		this.userRepository.save(
			user.setHearthpwnUserName(form.getHearthpwnUserName())
		);

		return new ModelAndView("redirect:/collection/in");
	}
}
