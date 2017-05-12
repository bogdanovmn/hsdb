package ru.bmn.web.hsdb.model.entity;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bmn.web.hsdb.model.entity.app.UserRole;
import ru.bmn.web.hsdb.model.entity.common.EntityWithUniqueName;
import ru.bmn.web.hsdb.model.entity.hs.*;
import ru.bmn.web.hsdb.model.repository.app.UserRoleRepository;
import ru.bmn.web.hsdb.model.repository.common.EntityWithUniqueNameRepository;
import ru.bmn.web.hsdb.model.repository.hs.*;

import java.util.HashMap;
import java.util.Map;

public class EntityFactory {
	private final Map<Class<?>, Map<String, EntityWithUniqueName>> cache = new HashMap<>();

	@Autowired
	private	ArtistRepository artistRepository;
	@Autowired
	private CharacterClassRepository characterClassRepository;
	@Autowired
	private RaceRepository raceRepository;
	@Autowired
	private RarityRepository rarityRepository;
	@Autowired
	private SeriesRepository seriesRepository;
	@Autowired
	private TypeRepository typeRepository;
	@Autowired
	private MechanicRepository mechanicRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;


	public EntityFactory() {}


	public EntityWithUniqueName getPersistEntity(EntityWithUniqueName entity) {
		EntityWithUniqueName result;

		Class<? extends EntityWithUniqueName> entityClass = entity.getClass();
		String name = entity.getName();

		if (!this.cache.containsKey(entityClass)) {
			this.cache.put(entityClass, new HashMap<>());
		}

		if (!this.cache.get(entityClass).containsKey(name)) {
			EntityWithUniqueNameRepository repository = this.getEntityRepository(entityClass);
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

	private EntityWithUniqueNameRepository getEntityRepository(Class<? extends EntityWithUniqueName> entityClass) {
		EntityWithUniqueNameRepository result = null;

		if (entityClass.equals(Artist.class))         result = this.artistRepository;
		if (entityClass.equals(CharacterClass.class)) result = this.characterClassRepository;
		if (entityClass.equals(Race.class))           result = this.raceRepository;
		if (entityClass.equals(Rarity.class))         result = this.rarityRepository;
		if (entityClass.equals(Series.class))         result = this.seriesRepository;
		if (entityClass.equals(Type.class))           result = this.typeRepository;
		if (entityClass.equals(Mechanic.class))       result = this.mechanicRepository;
		if (entityClass.equals(UserRole.class))       result = this.userRoleRepository;

		return result;
	}
}
