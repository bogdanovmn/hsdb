package ru.bmn.web.hsdb.model.entity.common;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DictionaryEntity extends EntityWithUniqueName {
	private String nameRu = "";
	private Integer sortValue = 0;

	public String getNameRu() {
		return nameRu;
	}

	public DictionaryEntity setNameRu(String nameRu) {
		this.nameRu = nameRu;
		return this;
	}

	public Integer getSortValue() {
		return sortValue;
	}

	public DictionaryEntity setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
		return this;
	}

	public DictionaryEntity setId(Integer id) {
		super.setId(id);
		this.setSortValue(id);
		return this;
	}
}
