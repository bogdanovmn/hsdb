package ru.bmn.web.hsdb.model.entity;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bmn.web.hsdb.model.entity.common.EntityWithUniqueName;
import ru.bmn.web.hsdb.model.repository.common.EntityWithUniqueNameRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityFactory {
	private final Map<Class<?>, Map<String, EntityWithUniqueName>> singleEntityCache = new ConcurrentHashMap<>();
	private final Map<Class<?>, Iterable> setEntityCache = new ConcurrentHashMap<>();
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
}
