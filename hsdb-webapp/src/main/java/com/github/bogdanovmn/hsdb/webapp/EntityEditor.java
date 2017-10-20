package com.github.bogdanovmn.hsdb.webapp;

import com.github.bogdanovmn.hsdb.model.User;
import com.github.bogdanovmn.hsdb.webapp.config.security.HsdbSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/entity/editor")
public class EntityEditor {

	@Autowired
	private HsdbSecurityService securityService;

	@GetMapping("/{entityName}")
	public ModelAndView form(
		Model model,
		@RequestHeader(name = "referer", required = false) String referer
	) {
		User user = this.securityService.getLoggedInUser();

		model.addAttribute("referer", referer);
		return new ModelAndView("entity_editor");
	}

	@PostMapping
	public ModelAndView update(
		@Valid EntityEditForm form,
		BindingResult bindingResult,
		Model model,
		@RequestHeader(name = "referer", required = false) String referer
	) {
		FormErrors formErrors = new FormErrors(bindingResult);

		User user = this.securityService.getLoggedInUser();

		if (formErrors.isNotEmpty()) {
			model.addAllAttributes(formErrors.getModel());
			model.addAttribute("referer", referer);
			return new ModelAndView("entity_editor");
		}

//		this.userRepository.save(
//			user.setHearthpwnUserName(form.getHearthpwnUserName())
//		);

		return new ModelAndView("redirect:/collection/in");
	}
}
