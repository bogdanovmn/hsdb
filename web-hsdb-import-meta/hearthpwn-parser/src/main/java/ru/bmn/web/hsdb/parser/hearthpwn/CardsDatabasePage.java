package ru.bmn.web.hsdb.parser.hearthpwn;

import com.github.bogdanovmn.downloadwlc.UrlContentDiscCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.bmn.web.hsdb.model.entity.hs.Card;
import ru.bmn.web.hsdb.model.entity.hs.CharacterClass;
import ru.bmn.web.hsdb.model.entity.hs.Type;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* default */ class CardsDatabasePage {
	private static final Logger LOG = LogManager.getLogger(CardsDatabasePage.class);

	private static final String PREFIX = Site.PREFIX + "cards?display=1&filter-premium=1&page=";

	private static final UrlContentDiscCache CACHE = new UrlContentDiscCache(
		CardsDatabasePage.class
	);

	private static final String DEFAULT_CHARACTER_CLASS_NAME = "Natural";

	private final int page;
	private Document htmlDocument;


	public CardsDatabasePage(int page) {
		this.page = page;
	}


	public int getPagesTotal()
		throws IOException
	{
		Elements pagingElements = this.getHtmlDocument()
			.select("ul[class^=b-pagination-list paging-list] li[class=b-pagination-item]");

		int result = 1;
		if (pagingElements.size() > 1) {
			for (Element pageItem : pagingElements) {
				if (!pageItem.text().equals("Next")) {
					String t = pageItem.text();
					result = Integer.valueOf(pageItem.text());
				}
			}
		}
		return result;
	}

	public List<Card> getCards()
		throws IOException
	{
		List<Card> result = new ArrayList<>();

		Elements rows = this.getHtmlDocument()
			.select("table[class^=listing listing-cards-tabular] tr[class~=^(even|odd)$]");

		for (Element row : rows) {
			// Skip "Hero" and "Hero power" card types
			String type = row.select("td[class=col-type").first().text();
			if (type.contains("Hero")) {
				continue;
			}

			Element nameElement = row.select("td[class=col-name").first();

			URL cardPageUrl = new URL(
				Site.PREFIX + nameElement.select("a[href^=/card]").first().attr("href")
			);

			CardPage cardPage = new CardPage(cardPageUrl);
			cardPage.parse();

			Card card = new Card();
			card.setName(
				nameElement.text()
			);
			card.setType(
				(Type) new Type().setName(type)
			)
			.setManaCost(
				Integer.valueOf(
					row.select("td[class=col-cost").first().text()
				)
			)
			.setAttack(
				Integer.valueOf(
					row.select("td[class=col-attack").first().text()
				)
			)
			.setHealth(
				Integer.valueOf(
					row.select("td[class=col-health").first().text()
				)
			)
			.setCharacters(
				Arrays.stream(
					row.select("td[class=col-class")
						.first().text().split("\\s?,\\s?")
				)
				.map(x -> (CharacterClass) new CharacterClass()
					.setName(
						x.isEmpty()
							? DEFAULT_CHARACTER_CLASS_NAME
							: x
					)
				)
				.collect(Collectors.toSet())
			)
			.setExternalUrl (cardPageUrl.toString())
			.setAboutText   (cardPage.getAboutText())
			.setArtist      (cardPage.getArtist())
			.setCollectible (cardPage.isCollectible())
			.setImageUrl    (cardPage.getImageUrl())
			.setGoldImageUrl(cardPage.getGoldImageUrl())
			.setMechanic    (cardPage.getMechanics())
			.setRace        (cardPage.getRace())
			.setRarity      (cardPage.getRarity())
			.setSeries      (cardPage.getSeries())
			.setSounds      (cardPage.getSounds())
			.setText        (cardPage.getText());

			LOG.info("Card '{}' parsed", card.getName());
			result.add(card);
		}

		return result;
	}

	private Document getHtmlDocument()
		throws IOException
	{
		if (this.htmlDocument == null) {
			String html = CACHE.getText(
				new URL(PREFIX + this.page)
			);

			this.htmlDocument = Jsoup.parse(html);
		}

		return this.htmlDocument;
	}
}
