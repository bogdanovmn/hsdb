package ru.bmn.web.hsdb.model.entity.hs;

import ru.bmn.web.hsdb.model.entity.hs.common.EntityWithUniqueNameTranslatable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class CharacterClass extends EntityWithUniqueNameTranslatable {
	@ManyToMany
	@JoinTable(
		name = "card2class",
		joinColumns = @JoinColumn(name = "class_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id")
	)
	private Set<Card> cards;

	public Set<Card> getCards() {
		return cards;
	}

	public CharacterClass setCards(Set<Card> cards) {
		this.cards = cards;
		return this;
	}
}
