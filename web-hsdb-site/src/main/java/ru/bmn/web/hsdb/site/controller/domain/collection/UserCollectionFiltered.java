package ru.bmn.web.hsdb.site.controller.domain.collection;

import ru.bmn.web.hsdb.model.entity.EntityFactory;
import ru.bmn.web.hsdb.model.entity.app.CollectionItem;
import ru.bmn.web.hsdb.model.entity.app.User;
import ru.bmn.web.hsdb.model.entity.hs.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
			.filter(x ->
				this.collectionFilter.isApplicable(
					x.getCard()
				)
			)
			.map(x ->
				new UserCollectionCard(
					allCharactersCard.get(x.getCard().getId()),
					x.getNormalCount(),
					x.getGoldCount()
				)
			)
			.collect(Collectors.toList());
	}

	public List<UserCollectionCard> getOutsideCards() {
		Map<Integer, Card> allCharactersCard = this.entityFactory.getCharactersCards(
			this.collectionFilter.getCharacterId()
		);

		Map<Integer, CollectionItem> collectionItemsMap = new HashMap<>();
		for (CollectionItem ci : user.getCollection()) {
			collectionItemsMap.put(ci.getCard().getId(), ci);
		}

		return allCharactersCard.values().stream()
			.filter(this.collectionFilter::isApplicable)
			.map(x -> {
				CollectionItem ci = collectionItemsMap.get(x.getId());

				return new UserCollectionCard(
					x,
					ci != null ? ci.getNormalCount() : 0,
					ci != null ? ci.getGoldCount() : 0
				);
			})
			.collect(Collectors.toList());
	}
}
