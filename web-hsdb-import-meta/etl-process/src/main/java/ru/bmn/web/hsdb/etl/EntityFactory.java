package ru.bmn.web.hsdb.etl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bmn.web.hsdb.model.entity.hs.*;
import ru.bmn.web.hsdb.model.repository.hs.*;
import ru.bmn.web.hsdb.model.repository.hs.common.EntityWithUniqueNameRepository;

import java.util.HashMap;
import java.util.Map;

public class EntityFactory {
	private final Map<Class<?>, Map<String, Object>> cache = new HashMap<>();

	@Autowired
	ArtistRepository artistRepository;
	@Autowired
	CharacterClassRepository characterClassRepository;
	@Autowired
	RaceRepository raceRepository;
	@Autowired
	RarityRepository rarityRepository;
	@Autowired
	SeriesRepository seriesRepository;
	@Autowired
	TypeRepository typeRepository;

	public EntityFactory() {}

	public Object getEntityByName(Class<?> entityClass, String name) {
		Object result = null;

		if (!this.cache.containsKey(entityClass)) {
			this.cache.put(entityClass, new HashMap<>());
		}
		if (!this.cache.get(entityClass).containsKey(name)) {
			result = this.getEntityRepository(entityClass).findFirstByName(name);
			this.cache.get(entityClass).put(name, result);
		}
		else {
			result = this.cache.get(entityClass).get(name);
		}

		return result;
	}

	private EntityWithUniqueNameRepository getEntityRepository(Class<?> entityClass) {
		EntityWithUniqueNameRepository result = null;

		if (entityClass.equals(Artist.class)) result = this.artistRepository;
		if (entityClass.equals(CharacterClass.class)) result = this.characterClassRepository;
		if (entityClass.equals(Race.class)) result = this.raceRepository;
		if (entityClass.equals(Rarity.class)) result = this.rarityRepository;
		if (entityClass.equals(Series.class)) result = this.seriesRepository;
		if (entityClass.equals(Type.class)) result = this.typeRepository;

		return result;
	}
}
