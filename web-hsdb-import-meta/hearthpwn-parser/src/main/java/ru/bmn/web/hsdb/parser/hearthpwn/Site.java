package ru.bmn.web.hsdb.parser.hearthpwn;


import ru.bmn.web.hsdb.model.entity.hs.Card;

import java.util.List;

public class Site {
	public static String PREFIX = "http://www.hearthpwn.com/";
	private static String CARD_LIST_PREFIX = PREFIX + "cards?display=1&filter-premium=1&page=";

	private String cachePrefix;

	public Site(String cachePrefix) {
		this.cachePrefix = cachePrefix;
	}

	public List<Card> getAllCards() {
		CachedUrlContent urlContent = new CachedUrlContent(this.cachePrefix, CARD_LIST_PREFIX + 1);
		return null;
	}

	public List<Card> getUserCards(String userName) {
		return null;
	}
}
