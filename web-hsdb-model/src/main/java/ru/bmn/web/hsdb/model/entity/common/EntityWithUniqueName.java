package ru.bmn.web.hsdb.model.entity.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.List;

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

	@Override
	public boolean equals(Object thatObj) {
		if (this == thatObj) {
			return true;
		}
		if (thatObj == null || getClass() != thatObj.getClass()) {
			return false;
		}
		if (this.getName() == null) {
			return false;
		}

		EntityWithUniqueName that = (EntityWithUniqueName) thatObj;

		return this.getName().equals(that.getName());
	}

	@Override
	public int hashCode() {
		return this.getName() == null
			? 0
			: this.getName().hashCode();
	}
}
