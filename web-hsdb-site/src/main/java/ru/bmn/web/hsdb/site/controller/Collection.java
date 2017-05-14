package ru.bmn.web.hsdb.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.bmn.web.hsdb.model.entity.EntityFactory;
import ru.bmn.web.hsdb.model.entity.app.User;
import ru.bmn.web.hsdb.model.repository.hs.CardRepository;
import ru.bmn.web.hsdb.site.controller.domain.CollectionFilter;
import ru.bmn.web.hsdb.site.controller.domain.FilterMenu;
import ru.bmn.web.hsdb.site.controller.domain.HeadMenu;
import ru.bmn.web.hsdb.site.security.HsdbSecurityService;

import java.util.HashMap;

@Controller
@RequestMapping("/collection")
public class Collection {
	@Autowired
	private EntityFactory entityFactory;
	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private HsdbSecurityService securityService;

	@GetMapping("/in")
	public ModelAndView collectionIn(
		@RequestParam(value = "rarity_id", defaultValue = "0") Integer rarityId,
		@RequestParam(value = "series_id", defaultValue = "0") Integer seriesId,
		@RequestParam(value = "character_id", defaultValue = "0") Integer characterId
	) {
//		this.cardRepository.findAll();

		User user = this.securityService.getLoggedInUser();

		CollectionFilter collectionFilter = new CollectionFilter(characterId, rarityId, seriesId);

		return new ModelAndView(
			"index",
			new HashMap<String, Object>() {{
				put("userName", user.getName());
				put("menu", new HeadMenu(HeadMenu.HMI_COLLECTION_IN).getItems());
				put("collectionPercent", 666); //Math.floor(100 * collection.total() / boosterCards.total()));
				put("type", "in");
				put("filter", new FilterMenu(entityFactory).getItems(collectionFilter));

			}}
		);
	}
}
