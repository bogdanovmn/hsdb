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

public class EntityMapFactory {
	private Map<Class<? extends EntityWithUniqueName>, EntityWithUniqueNameRepository> map;

	@Autowired
	private ArtistRepository artistRepository;
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

	public EntityMapFactory() {
	}

	private void init() {
		this.map = new HashMap<Class<? extends EntityWithUniqueName>, EntityWithUniqueNameRepository>()
		{{
			put(Artist.class         , artistRepository);
			put(CharacterClass.class , characterClassRepository);
			put(Race.class           , raceRepository);
			put(Rarity.class         , rarityRepository);
			put(Series.class         , seriesRepository);
			put(Type.class           , typeRepository);
			put(Mechanic.class       , mechanicRepository);
			put(UserRole.class       , userRoleRepository);
		}};
	}

	public EntityWithUniqueNameRepository getRepository(Class<? extends EntityWithUniqueName> aClass) {
		return this.map.get(aClass);
	}
}
