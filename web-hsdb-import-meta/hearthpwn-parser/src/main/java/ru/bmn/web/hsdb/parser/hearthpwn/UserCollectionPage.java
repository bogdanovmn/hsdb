package ru.bmn.web.hsdb.parser.hearthpwn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.bmn.web.hsdb.model.entity.app.CollectionItem;
import ru.bmn.web.hsdb.model.entity.hs.Card;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/* default */ class UserCollectionPage {
	private static final Logger LOG = LogManager.getLogger(UserCollectionPage.class);

	private static final UrlContentDiscCache CACHE = new UrlContentDiscCache(
		UserCollectionPage.class
	);

	private final String userName;
	private final String pageUrl;
	private Document htmlDocument;

	public UserCollectionPage(String userName) {
		this.userName = userName;
		this.pageUrl = String.format("%s/members/%s/collection", Site.PREFIX, userName);
	}


	public Set<CollectionItem> getCollection()
		throws IOException
	{
		List<CollectionItem> result = new ArrayList<>();

		Elements items = this.getHtmlDocument()
			.select("div[class=card-image-item owns-card]");


		Map<String, CollectionItem> collectionItemMap = new HashMap<>();
		for (Element div : items) {
			String cardName = div.attr("data-card-name");

			boolean isGold = Boolean.valueOf(div.attr("data-is-gold"));

			int count = Integer.valueOf(
				div.select("span[class=inline-card-count]").first()
					.attr("data-card-count")
			);

			LOG.info(
				"Found card '{}': {} {}",
					cardName,
					count,
					isGold ? "gold" : ""
			);

			Card card = new Card();
			card.setName(cardName);

			CollectionItem collectionItem = collectionItemMap.computeIfAbsent(
				cardName,
				k -> new CollectionItem().setCard(card)
			);

			if (isGold) {
				collectionItem.setGoldCount(count);
			}
			else {
				collectionItem.setNormalCount(count);
			}
		}

		return collectionItemMap.values().stream().collect(Collectors.toSet());
	}

	private Document getHtmlDocument()
		throws IOException
	{
		if (this.htmlDocument == null) {
			String html = CACHE.getText(
				new URL(this.pageUrl)
			);

			this.htmlDocument = Jsoup.parse(html);
		}

		return this.htmlDocument;
	}
}
