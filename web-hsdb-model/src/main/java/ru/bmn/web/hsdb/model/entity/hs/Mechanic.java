package ru.bmn.web.hsdb.model.entity.hs;

import ru.bmn.web.hsdb.model.entity.hs.common.EntityWithUniqueNameTranslatable;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Mechanic extends EntityWithUniqueNameTranslatable {
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
