package ru.bmn.web.hsdb.model.entity.common;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class DictionaryEntity extends EntityWithUniqueName {
	private String nameRu = "";
	private Integer order = 0;

	public String getNameRu() {
		return nameRu;
	}

	public DictionaryEntity setNameRu(String nameRu) {
		this.nameRu = nameRu;
		return this;
	}

	public Integer getOrder() {
		return order;
	}

	public DictionaryEntity setOrder(Integer order) {
		this.order = order;
		return this;
	}

	public DictionaryEntity setId(Integer id) {
		super.setId(id);
		this.setOrder(id);
		return this;
	}
}
