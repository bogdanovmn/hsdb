package ru.bmn.web.hsdb.model.entity.hs;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Mechanic {
	@Id
	@GeneratedValue
//	@Column(name = "mechanic_id")
	private Integer id;

	private String name;
	private String nameRu = "";

	@ManyToMany
	@JoinTable(
		name = "card2mechanic",
		joinColumns = @JoinColumn(name = "mechanic_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id")
	)
	private Set<Card> cards;

	public Integer getId() {
		return id;
	}

	public Mechanic setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Mechanic setName(String name) {
		this.name = name;
		return this;
	}

	public String getNameRu() {
		return nameRu;
	}

	public Mechanic setNameRu(String nameRu) {
		this.nameRu = nameRu;
		return this;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public Mechanic setCards(Set<Card> cards) {
		this.cards = cards;
		return this;
	}
}
