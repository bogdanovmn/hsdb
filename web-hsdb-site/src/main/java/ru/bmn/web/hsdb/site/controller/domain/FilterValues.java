package ru.bmn.web.hsdb.site.controller.domain;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bmn.web.hsdb.model.entity.EntityFactory;
import ru.bmn.web.hsdb.model.entity.common.DictionaryEntity;
import ru.bmn.web.hsdb.model.entity.common.EntityWithUniqueName;
import ru.bmn.web.hsdb.model.entity.hs.CharacterClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FilterValues {
	private final Class<? extends DictionaryEntity> dictEntClass;
	private final Integer selectedId;

	@Autowired
	EntityFactory entityFactory;

	public FilterValues(Class<? extends DictionaryEntity> dictEntityClass, Integer selectedId) {
		this.dictEntClass = dictEntityClass;
		this.selectedId = selectedId;
	}

	public List<FilterValuesItem> getItems() {

		Iterable entities = this.entityFactory.getAll(this.dictEntClass);

		List<DictionaryEntity> dictEntities = new ArrayList<>();

		for (Object obj : entities) {
			dictEntities.add((DictionaryEntity) obj);
		}

		return dictEntities.stream()
			.sorted(
				Comparator.comparingInt(DictionaryEntity::getOrder)
			)
			.map(
				x -> {
					FilterValuesItem item = new FilterValuesItem(x.getId(), x.getName());
					if (x.getId().equals(this.selectedId)) {
						item.setSelected(true);
					}
					return item;
				}
			)
			.collect(Collectors.toList());
	}
}
