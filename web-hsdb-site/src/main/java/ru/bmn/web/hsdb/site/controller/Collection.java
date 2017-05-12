package ru.bmn.web.hsdb.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.bmn.web.hsdb.model.repository.hs.CardRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/collection")
public class Collection {

	@Autowired
	CardRepository cardRepository;

	@GetMapping("/in")
	public ModelAndView collectionIn(
		@RequestParam("rarity_id") Optional<Integer> rarityId,
		@RequestParam("series_id") Optional<Integer> seriesId,
		@RequestParam("character_id") Optional<Integer> characterId
	) {
//		this.cardRepository.findAll();

		Map<String, Object> model = new HashMap<>();
		model.put("userId", 1);
		return new ModelAndView("index", model);
//		BoosterCards boosterCards = (BoosterCards) this.getServletContext().getAttribute("boosterCards");
//		UserCollection collection = new UserCollection(
//			(Connection) this.getServletContext().getAttribute("dbConnection"),
//			(Integer)    req.getSession().getAttribute("userId")
//		);
//
//		ViewCollectionCards cards = new ViewCollectionCards(collection, boosterCards);
//
//
//		req.setAttribute("cards", cards.getInItems(characterFilter, rarityFilter, setFilter));
//
//		req.setAttribute("type", "in");
//		req.setAttribute("collection_percent", Math.floor(100 * collection.total() / boosterCards.total()));
//		req.setAttribute("filter_character", new QueryFilter(characterFilter));
//		req.setAttribute("filter_set", cards);
//		req.setAttribute("filter_rarity", cards);
//		req.setAttribute("menu", new HeadMenu("collection_in").getItems());
//		req.setAttribute("filter_params", cards);
//				/*filter_character   => $filter_character,
//				filter_set         => $filter_set,
//				filter_rarity      => $self->_prepare_filter('rarity', $rarity_id),
//				_prepare_filter_params(character_id => $character_id, rarity_id => $rarity_id, set_id => $set_id),
//				*/
//
	}
}
