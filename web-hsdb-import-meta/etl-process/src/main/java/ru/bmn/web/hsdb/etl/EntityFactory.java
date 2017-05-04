package ru.bmn.web.hsdb.etl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.hs.Artist;
import ru.bmn.web.hsdb.model.repository.hs.ArtistRepository;
import ru.bmn.web.hsdb.model.repository.hs.common.EntityWithUniqueNameRepository;

import java.util.HashMap;
import java.util.Map;

public class EntityFactory {
	private final Map<Class<?>, Map<String, Object>> cache = new HashMap<>();

	@Autowired
	ArtistRepository artistRepository;

	public EntityFactory() {}

	public Object getEntityByName(Class<?> entityClass, String name) {
		if (!this.cache.containsKey(entityClass)) {
			this.cache.put(entityClass, new HashMap<>());
		}
		if (!this.cache.get(entityClass).containsKey(name)) {
			this.cache.get(entityClass)
				.put(name, this.getEntityRepository(entityClass));
		}

		return null;
	}

	private EntityWithUniqueNameRepository getEntityRepository(Class<?> entityClass) {
		EntityWithUniqueNameRepository result = null;

		if (entityClass.equals(Artist.class)) result = this.artistRepository;

		return result;
	}
}
