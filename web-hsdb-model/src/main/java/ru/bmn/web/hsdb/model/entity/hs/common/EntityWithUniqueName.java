package ru.bmn.web.hsdb.model.entity.hs.common;

import javax.persistence.*;

@MappedSuperclass
@Table(
	uniqueConstraints = {
		@UniqueConstraint(
			columnNames = {"name"}
		)
	}
)
public abstract class EntityWithUniqueName {
	@Id
	@GeneratedValue
	protected Integer id;

	@Column(nullable = false)
	protected String name;


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
