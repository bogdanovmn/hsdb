package ru.bmn.web.hsdb.parser.hearthpwn;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CardsDatabasePage {
	private static String PREFIX = Site.PREFIX + "cards?display=1&filter-premium=1&page=";

	private final int page;
	private Document html;

	public CardsDatabasePage(int page) {
		this.page = page;
	}


	public int getPagesTotal()
		throws IOException
	{
		this.html = Jsoup.connect(PREFIX + this.page).get();

		Elements pagingElements = this.html.select("ul[class^=b-pagination-list]");
		int result = 1;
		if (pagingElements.size() > 1) {
			for (Element pageItem : pagingElements.select("li[class=b-pagination-item")) {
				if (!pageItem.text().equals("Next")) {
					result = Integer.valueOf(pageItem.text());
				}
			}
		}
		return result;
	}
}
