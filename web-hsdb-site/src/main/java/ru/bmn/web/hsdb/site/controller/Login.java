package ru.bmn.web.hsdb.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class Login {
	@GetMapping
	public ModelAndView form(Model model, String error, String logout) {
		if (error != null) {
			model.addAttribute("error", "Your username and password is invalid.");
		}

		return new ModelAndView("login", model.asMap());
	}
}
