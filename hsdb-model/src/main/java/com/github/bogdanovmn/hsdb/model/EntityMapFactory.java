package com.github.bogdanovmn.hsdb.model;

import org.springframework.beans.factory.annotation.Autowired;

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
	@Autowired
	private CardRepository cardRepository;

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
			put(Card.class           , cardRepository);
		}};
	}

	public EntityWithUniqueNameRepository getRepository(Class<? extends EntityWithUniqueName> aClass) {
		return this.map.get(aClass);
	}
}
