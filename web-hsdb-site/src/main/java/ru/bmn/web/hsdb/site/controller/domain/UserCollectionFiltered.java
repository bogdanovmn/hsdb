package ru.bmn.web.hsdb.site.controller.domain;

import ru.bmn.web.hsdb.model.entity.EntityFactory;
import ru.bmn.web.hsdb.model.entity.app.CollectionItem;
import ru.bmn.web.hsdb.model.entity.app.User;
import ru.bmn.web.hsdb.model.entity.hs.Card;

import java.util.*;
import java.util.stream.Collectors;

public class UserCollectionFiltered {
	private final User user;
	private final EntityFactory entityFactory;
	private final CollectionFilter collectionFilter;

	public UserCollectionFiltered(User user, EntityFactory entityFactory, CollectionFilter collectionFilter) {
		this.user = user;
		this.entityFactory = entityFactory;
		this.collectionFilter = collectionFilter;
	}

	public List<UserCollectionCard> getCards() {

		Map<Integer, Card> allCharactersCard = this.entityFactory.getCharactersCards(
			this.collectionFilter.getCharacterId()
		);

		Set<CollectionItem> collectionItems = user.getCollection();
		return collectionItems.stream()
			.map(x ->
				new UserCollectionCard(
					allCharactersCard.get(x.getCard().getId()),
					x.getNormalCount(),
					x.getGoldCount()
				)
			)
			.collect(Collectors.toList());
	}
}
