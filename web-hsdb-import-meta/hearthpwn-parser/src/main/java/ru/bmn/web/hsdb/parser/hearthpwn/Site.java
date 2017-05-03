package ru.bmn.web.hsdb.parser.hearthpwn;


import ru.bmn.web.hsdb.model.entity.hs.Card;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Site {
	public static String PREFIX = "http://www.hearthpwn.com/";


	public Site() {
	}

	public List<Card> getAllCards()
		throws IOException
	{
		List<Card> result = new ArrayList<>();

		CardsDatabasePage cardsDatabasePage = new CardsDatabasePage(1);
		int totalPages = cardsDatabasePage.getPagesTotal();

		result.addAll(cardsDatabasePage.getCards());

		for (int i = 2; i < totalPages; i++) {
			result.addAll(
				new CardsDatabasePage(i).getCards()
			);
		}
		return null;
	}

	public List<Card> getUserCards(String userName) {
		return null;
	}
}
