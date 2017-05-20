package ru.bmn.web.hsdb.site.controller.domain.collection;

import ru.bmn.web.hsdb.model.entity.EntityFactory;
import ru.bmn.web.hsdb.model.entity.hs.CharacterClass;
import ru.bmn.web.hsdb.model.entity.hs.Rarity;
import ru.bmn.web.hsdb.model.entity.hs.Series;
import ru.bmn.web.hsdb.site.controller.domain.common.FilterValues;

import java.util.HashMap;
import java.util.Map;

public class CollectionFilterMenu {
	private final EntityFactory entityFactory;

	public CollectionFilterMenu(EntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	public Map<String, Object> getItems(CollectionFilter filter) {
		return new HashMap<String, Object>()
		{{
			put("characterId", filter.getCharacterId());
			put(
				"characterFilter",
				new FilterValues(
					entityFactory.getAll(CharacterClass.class),
					filter.getCharacterId()
				).getItems()
			);
			put("currentFilterWOCharacterId", filter.getParamsWOSpecified(CollectionFilter.CHARACTER_ID));

			put("rarityId", filter.getRarityId());
			put(
				"rarityFilter",
				new FilterValues(
					entityFactory.getAll(Rarity.class),
					filter.getRarityId()
				).getItems()
			);
			put("currentFilterWORarityId", filter.getParamsWOSpecified(CollectionFilter.RARITY_ID));

			put("seriesId", filter.getSeriesId());
			put(
				"seriesFilter",
				new FilterValues(
					entityFactory.getAll(Series.class),
					filter.getSeriesId()
				).getItems()
			);
			put("currentFilterWOSeriesId", filter.getParamsWOSpecified(CollectionFilter.SERIES_ID));
		}};
	}
}
