package ru.bmn.web.hsdb.model.entity.hs;

import ru.bmn.web.hsdb.model.entity.common.DictionaryEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class CharacterClass extends DictionaryEntity {
	@ManyToMany(mappedBy = "characters")
	private Set<Card> cards;

	public Set<Card> getCards() {
		return cards;
	}

	public CharacterClass setCards(Set<Card> cards) {
		this.cards = cards;
		return this;
	}
}
