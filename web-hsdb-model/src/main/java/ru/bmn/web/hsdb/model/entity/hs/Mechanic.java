package ru.bmn.web.hsdb.model.entity.hs;

import ru.bmn.web.hsdb.model.entity.hs.common.EntityWithUniqueNameTranslatable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Mechanic extends EntityWithUniqueNameTranslatable {
	private String description;

	@ManyToMany
	@JoinTable(
		name = "card2mechanic",
		joinColumns = @JoinColumn(name = "mechanic_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id")
	)
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
