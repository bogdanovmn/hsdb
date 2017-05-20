package ru.bmn.web.hsdb.site.controller.domain.collection;

import ru.bmn.web.hsdb.model.entity.hs.Card;

public class UserCollectionCard {
	private final Card card;
	private final int normalCount;
	private final int goldCount;

	public UserCollectionCard(Card card, int normalCount, int goldCount) {
		this.card = card;
		this.normalCount = normalCount;
		this.goldCount = goldCount;
	}

	public Card getCard() {
		return card;
	}

	public int getNormalCount() {
		return normalCount;
	}

	public int getGoldCount() {
		return goldCount;
	}
}
