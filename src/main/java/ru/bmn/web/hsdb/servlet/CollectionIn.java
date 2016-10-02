package ru.bmn.web.hsdb.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.bmn.web.hsdb.BoosterCards;
import ru.bmn.web.hsdb.Card;
import ru.bmn.web.hsdb.HeadMenu;
import ru.bmn.web.hsdb.UserCollection;


public class CollectionIn extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoosterCards boosterCards = (BoosterCards) this.getServletContext().getAttribute("boosterCards");
		UserCollection collection = new UserCollection(
			(Connection) this.getServletContext().getAttribute("dbConnection"), 
			(Integer)    req.getSession().getAttribute("user_id")
		);
		List<Card> cards = null;
		req.setAttribute("cards", cards); 
					/*sort { $a->{mana_cost} <=> $b->{mana_cost} or $a->{id} <=> $b->{id} }
					grep { 
						(not $rarity_id or $rarity_id eq $_->{rarity_id})
						and
						(not $set_id or $set_id eq $_->{set_id})
					}
					map  { 
						my $data = $__ALL_CARDS->{by_id}->{$_};
						$data->{norm_count} = $collection{$_} ? $collection{$_}->{norm_count} : 0;
						$data->{gold_count} = $collection{$_} ? $collection{$_}->{gold_count} : 0;
						$data;
					} 
					@card_ids */
				],
		req.setAttribute("type", "in"); 
		req.setAttribute("collection_percent", Math.floor(100 * collection.total() / boosterCards.total())); 
		req.setAttribute("filter_character", cards);
		req.setAttribute("filter_set", cards);
		req.setAttribute("filter_rarity", cards);
		req.setAttribute("menu", new HeadMenu("collection_in").items());
		req.setAttribute("filter_params", cards);
				filter_character   => $filter_character,
				filter_set         => $filter_set,
				filter_rarity      => $self->_prepare_filter('rarity', $rarity_id),
				_prepare_filter_params(character_id => $character_id, rarity_id => $rarity_id, set_id => $set_id),
		
		
		getServletContext().getRequestDispatcher("/collection.jsp").forward(req, resp);
	}
}