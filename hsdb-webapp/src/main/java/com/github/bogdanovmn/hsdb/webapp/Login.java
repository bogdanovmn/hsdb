package com.github.bogdanovmn.hsdb.webapp;

import com.github.bogdanovmn.hsdb.webapp.config.security.HsdbSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {
	@Autowired
	private HsdbSecurityService securityService;

	@GetMapping("/login")
	public ModelAndView form(Model model, String error) {
		if (this.securityService.isLogged()) {
			return new ModelAndView("redirect:" + App.HOME_PAGE);
		}

		if (error != null) {
			model.addAttribute("customError", "Попробуйте еще разок");
		}

		return new ModelAndView("login", model.asMap());
	}
}
