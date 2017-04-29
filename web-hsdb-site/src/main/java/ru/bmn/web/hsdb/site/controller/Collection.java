package ru.bmn.web.hsdb.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bmn.web.hsdb.model.repository.CardRepository;

@Controller
@RequestMapping("/collection")
public class Collection {

	@Autowired
	CardRepository cardRepository;

	@GetMapping("/in")
	public void collectionIn() {
		this.cardRepository.findAll();
	}
}
