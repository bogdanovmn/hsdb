package ru.bmn.web.hsdb.model.entity.hs;

import javax.persistence.*;

@Entity
@Table(
	uniqueConstraints = {
		@UniqueConstraint(
			columnNames = {"name"}
		)
	}
)
public class Type {
	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	private String nameRu = "";

	public Integer getId() {
		return id;
	}

	public Type setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Type setName(String name) {
		this.name = name;
		return this;
	}

	public String getNameRu() {
		return nameRu;
	}

	public Type setNameRu(String nameRu) {
		this.nameRu = nameRu;
		return this;
	}
}
