package ru.bmn.web.hsdb.model.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.bmn.web.hsdb.model.entity.common.EntityWithUniqueName;
import ru.bmn.web.hsdb.model.repository.common.EntityWithUniqueNameRepository;

import java.util.HashMap;
import java.util.Map;

public class EntityFactory {
	private final Map<Class<?>, Map<String, EntityWithUniqueName>> cache = new HashMap<>();

	@Autowired
	private EntityMapFactory entityMapFactory;

	public EntityFactory() {
	}

	public EntityWithUniqueName getPersistEntityWithUniqueName(EntityWithUniqueName entity) {
		EntityWithUniqueName result;

		Class<? extends EntityWithUniqueName> entityClass = entity.getClass();
		String name = entity.getName();

		if (!this.cache.containsKey(entityClass)) {
			this.cache.put(entityClass, new HashMap<>());
		}

		if (!this.cache.get(entityClass).containsKey(name)) {
			EntityWithUniqueNameRepository repository = entityMapFactory.getRepository(entityClass);
			result = repository.findFirstByName(name);

			if (result != null) {
				this.cache.get(entityClass).put(name, result);
			}
			else {
				repository.save(entity);
				result = entity;
			}
		}
		else {
			result = this.cache.get(entityClass).get(name);
		}

		return result;
	}

	public Iterable getAll(Class<? extends EntityWithUniqueName> entClass) {
		return entityMapFactory.getRepository(entClass).findAll();
	}
}
