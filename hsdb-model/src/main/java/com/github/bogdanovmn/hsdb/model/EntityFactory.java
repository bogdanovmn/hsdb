package com.github.bogdanovmn.hsdb.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityFactory {
	private final Map<Class<?>, Map<String, EntityWithUniqueName>> singleEntityCache = new ConcurrentHashMap<>();
	private final Map<Class<?>, Iterable> setEntityCache = new ConcurrentHashMap<>();
	private final Map<Integer, Map<Integer, Card>> cardsCache = new ConcurrentHashMap<>();
	private final Map<String, Card> cardByNameCache = new ConcurrentHashMap<>();

	@Autowired
	private EntityMapFactory entityMapFactory;

	public EntityFactory() {}


	public EntityWithUniqueName getPersistEntityWithUniqueName(EntityWithUniqueName entity) {
		EntityWithUniqueName result;

		Class<? extends EntityWithUniqueName> entityClass = entity.getClass();
		String name = entity.getName();

		if (!this.singleEntityCache.containsKey(entityClass)) {
			this.singleEntityCache.put(entityClass, new HashMap<>());
		}

		if (!this.singleEntityCache.get(entityClass).containsKey(name)) {
			EntityWithUniqueNameRepository repository = entityMapFactory.getRepository(entityClass);
			result = repository.findFirstByName(name);

			if (result != null) {
				this.singleEntityCache.get(entityClass).put(name, result);
			}
			else {
				repository.save(entity);
				result = entity;
			}
		}
		else {
			result = this.singleEntityCache.get(entityClass).get(name);
		}

		return result;
	}

	public Iterable getAll(Class<? extends EntityWithUniqueName> entClass) {
		return this.setEntityCache.computeIfAbsent(
			entClass,
			x -> entityMapFactory.getRepository(entClass).findAll()
		);
	}

	public Map<Integer, Card> getCharactersCards(Integer characterId) {
		Map<Integer, Card> result = this.cardsCache.get(characterId);

		if (result == null) {
			Iterable allCards = this.getAll(Card.class);
			for (Object cardObjRef : allCards) {
				Card card = (Card) cardObjRef;
				for (CharacterClass characterClass : card.getCharacters()) {
					Map<Integer, Card> map = this.cardsCache.computeIfAbsent(
						characterClass.getId(), x -> new HashMap<>()
					);
					map.put(card.getId(), card);
				}
			}
		}

		return this.cardsCache.get(characterId);
	}

	public Card getCardByName(String name) {
		if (this.cardByNameCache.isEmpty()) {
			Iterable allCards = this.getAll(Card.class);
			for (Object cardObjRef : allCards) {
				Card card = (Card) cardObjRef;
				this.cardByNameCache.put(card.getName(), card);
			}
		}
		return this.cardByNameCache.get(name);
	}
}