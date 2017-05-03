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
public class Rarity {
	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	private String nameRu = "";

	public Integer getId() {
		return id;
	}

	public Rarity setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Rarity setName(String name) {
		this.name = name;
		return this;
	}

	public String getNameRu() {
		return nameRu;
	}

	public Rarity setNameRu(String nameRu) {
		this.nameRu = nameRu;
		return this;
	}
}
