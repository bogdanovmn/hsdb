package com.github.bogdanovmn.hsdb.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class EntityWithUniqueName {
	@Id
	@GeneratedValue
	@Access(AccessType.PROPERTY)
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
		this.name = name
			.replaceAll("\u00A0","")
			.replaceFirst("^\\s+", "")
			.replaceFirst("\\s+$", "")
			.replaceAll("\\s+", " ")
			.replaceAll("O[^a-zA-Z](\\w)", "O'$1")
			.replaceAll("(\\w[.,;:])(\\w)", "$1 $2");

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
