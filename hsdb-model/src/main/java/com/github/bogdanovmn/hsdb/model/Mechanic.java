package com.github.bogdanovmn.hsdb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Mechanic extends DictionaryEntity {
	@Column(length = 500)
	private String description;

	@ManyToMany(mappedBy = "mechanic")
	private Set<Card> cards;

	public Set<Card> getCards() {
		return cards;
	}

	public Mechanic setCards(Set<Card> cards) {
		this.cards = cards;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Mechanic setDescription(String description) {
		this.description = description;
		return this;
	}
}
