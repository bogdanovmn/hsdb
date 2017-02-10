package ru.bmn.web.hsdb.servlet;

import ru.bmn.web.hsdb.domain.BoosterCards;
import ru.bmn.web.hsdb.domain.UserCollection;
import ru.bmn.web.hsdb.servlet.view.ui.HeadMenu;
import ru.bmn.web.hsdb.servlet.view.ui.QueryFilter;
import ru.bmn.web.hsdb.servlet.view.ui.ViewCollectionCards;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


public class CollectionIn extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rarityFilter    = req.getParameter("rarity_id");
		String setFilter       = req.getParameter("set_id");
		String characterFilter = req.getParameter("character_id");

		BoosterCards boosterCards = (BoosterCards) this.getServletContext().getAttribute("boosterCards");
		UserCollection collection = new UserCollection(
			(Connection) this.getServletContext().getAttribute("dbConnection"),
			(Integer)    req.getSession().getAttribute("userId")
		);

		ViewCollectionCards cards = new ViewCollectionCards(collection, boosterCards);


		req.setAttribute("cards", cards.getInItems(characterFilter, rarityFilter, setFilter));

		req.setAttribute("type", "in"); 
		req.setAttribute("collection_percent", Math.floor(100 * collection.total() / boosterCards.total())); 
		req.setAttribute("filter_character", new QueryFilter(characterFilter));
		req.setAttribute("filter_set", cards);
		req.setAttribute("filter_rarity", cards);
		req.setAttribute("menu", new HeadMenu("collection_in").getItems());
		req.setAttribute("filter_params", cards);
				/*filter_character   => $filter_character,
				filter_set         => $filter_set,
				filter_rarity      => $self->_prepare_filter('rarity', $rarity_id),
				_prepare_filter_params(character_id => $character_id, rarity_id => $rarity_id, set_id => $set_id),
				*/
		
		getServletContext().getRequestDispatcher("/collection.jsp").forward(req, resp);
	}
}