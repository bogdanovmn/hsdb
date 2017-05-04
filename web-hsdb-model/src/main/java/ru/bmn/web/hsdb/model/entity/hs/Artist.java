package ru.bmn.web.hsdb.model.entity.hs;

import ru.bmn.web.hsdb.model.entity.hs.common.EntityWithUniqueName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Artist extends EntityWithUniqueName {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String name;


	public Integer getId() {
		return id;
	}

	public Artist setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Artist setName(String name) {
		this.name = name;
		return this;
	}
}
