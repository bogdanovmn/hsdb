package com.github.bogdanovmn.hsdb.parser.hearthpwn;

import com.github.bogdanovmn.downloadwlc.UrlContentDiskCache;
import com.github.bogdanovmn.hsdb.model.Card;
import com.github.bogdanovmn.hsdb.model.CollectionItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/* default */ class UserCollectionPage {
	private static final Logger LOG = LoggerFactory.getLogger(UserCollectionPage.class);

	private static final UrlContentDiskCache CACHE = new UrlContentDiskCache(
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

		return new HashSet<>(collectionItemMap.values());
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
