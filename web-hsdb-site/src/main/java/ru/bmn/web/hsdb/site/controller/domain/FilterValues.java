package ru.bmn.web.hsdb.site.controller.domain;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bmn.web.hsdb.model.entity.EntityFactory;
import ru.bmn.web.hsdb.model.entity.common.DictionaryEntity;
import ru.bmn.web.hsdb.model.entity.common.EntityWithUniqueName;
import ru.bmn.web.hsdb.model.entity.hs.CharacterClass;

import java.util.Collections;
import java.util.List;

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

//		List<FilterValues> res = entities.spliterator();
		return Collections.emptyList();
	}
}
