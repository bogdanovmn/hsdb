package com.github.bogdanovmn.hsdb.webapp;

import com.github.bogdanovmn.hsdb.model.CardRepository;
import com.github.bogdanovmn.hsdb.model.EntityFactory;
import com.github.bogdanovmn.hsdb.model.User;
import com.github.bogdanovmn.hsdb.webapp.config.security.HsdbSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

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
		Integer rarityId,
		Integer seriesId,
		Integer characterId
	) {
		User user = this.securityService.getLoggedInUser();

		CollectionFilter collectionFilter = new CollectionFilter(characterId, rarityId, seriesId);
		UserCollectionFiltered collection = new UserCollectionFiltered(
			user, this.entityFactory, collectionFilter
		);
		List<UserCollectionCard> cards = collection.getCards();

		return new ModelAndView(
			"collection",
			new HashMap<String, Object>() {{
				put("userName", user.getName());
				put("menu", new HeadMenu(HeadMenu.HMI_COLLECTION_IN).getItems());
				put("collectionPercent", 666); //Math.floor(100 * collection.total() / boosterCards.total()));
				put("type", "in");
				put("filter", new CollectionFilterMenu(entityFactory).getItems(collectionFilter));
				put("cards", cards);
			}}
		);
	}

	@GetMapping("/out")
	public ModelAndView collectionOut(
		Integer rarityId,
		Integer seriesId,
		Integer characterId
	) {
		User user = this.securityService.getLoggedInUser();

		CollectionFilter collectionFilter = new CollectionFilter(characterId, rarityId, seriesId);
		UserCollectionFiltered collection = new UserCollectionFiltered(
			user, this.entityFactory, collectionFilter
		);
		List<UserCollectionCard> cards = collection.getOutsideCards();

		return new ModelAndView(
			"collection",
			new HashMap<String, Object>() {{
				put("userName", user.getName());
				put("menu", new HeadMenu(HeadMenu.HMI_COLLECTION_OUT).getItems());
				put("collectionPercent", 999); //Math.floor(100 * collection.total() / boosterCards.total()));
				put("type", "out");
				put("filter", new CollectionFilterMenu(entityFactory).getItems(collectionFilter));
				put("cards", cards);
			}}
		);
	}
}
