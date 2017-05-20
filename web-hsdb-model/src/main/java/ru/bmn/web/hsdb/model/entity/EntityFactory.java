package ru.bmn.web.hsdb.model.entity;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bmn.web.hsdb.model.entity.common.EntityWithUniqueName;
import ru.bmn.web.hsdb.model.entity.hs.Card;
import ru.bmn.web.hsdb.model.repository.common.EntityWithUniqueNameRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityFactory {
	private final Map<Class<?>, Map<String, EntityWithUniqueName>> singleEntityCache = new ConcurrentHashMap<>();
	private final Map<Class<?>, Iterable> setEntityCache = new ConcurrentHashMap<>();
	private final Map<Integer, Map<Integer, Card>> cardsCache = new HashMap<>();
	@Autowired
	private EntityMapFactory entityMapFactory;

	public EntityFactory() {
	}

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
				Map<Integer, Card> map = this.cardsCache.computeIfAbsent(
					characterId, x -> new HashMap<>()
				);
				map.put(card.getId(), card);
			}
		}

		return result;
	}
}