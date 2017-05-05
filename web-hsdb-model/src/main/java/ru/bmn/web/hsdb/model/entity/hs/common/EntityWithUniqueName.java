package ru.bmn.web.hsdb.model.entity.hs.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityWithUniqueName {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(unique = true, nullable = false)
	private String name;


	public Integer getId() {
		return id;
	}

	public EntityWithUniqueName setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public EntityWithUniqueName setName(String name) {
		this.name = name;
		return this;
	}
}
