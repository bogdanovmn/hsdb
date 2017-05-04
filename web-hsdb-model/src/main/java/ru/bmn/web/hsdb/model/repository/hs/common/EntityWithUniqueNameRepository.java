package ru.bmn.web.hsdb.model.repository.hs.common;


public interface EntityWithUniqueNameRepository<EntityType> {
	public EntityType findFirstByName(String name);
}
