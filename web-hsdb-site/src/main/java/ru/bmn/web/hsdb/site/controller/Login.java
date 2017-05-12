package ru.bmn.web.hsdb.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.bmn.web.hsdb.site.security.HsdbSecurityService;

@Controller
@RequestMapping("/login")
public class Login {
	@Autowired
	private HsdbSecurityService securityService;

	@GetMapping
	public String form(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

//	@PostMapping
//	public ModelAndView login(
//		@RequestParam("username") String name,
//		@RequestParam("password") String password
//	) {
//		this.securityService.login(
//			name,
//			password
//		);
//
//		return new ModelAndView("redirect:/collection/in");
//	}
}
