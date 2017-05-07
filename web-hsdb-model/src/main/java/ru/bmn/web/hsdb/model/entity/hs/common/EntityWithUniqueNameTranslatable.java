package ru.bmn.web.hsdb.model.entity.hs.common;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityWithUniqueNameTranslatable extends EntityWithUniqueName {
	private String nameRu = "";

	public String getNameRu() {
		return nameRu;
	}

	public EntityWithUniqueNameTranslatable setNameRu(String nameRu) {
		this.nameRu = nameRu;
		return this;
	}
}
