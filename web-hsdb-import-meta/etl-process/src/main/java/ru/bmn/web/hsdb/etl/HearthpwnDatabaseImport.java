package ru.bmn.web.hsdb.etl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bmn.web.hsdb.model.entity.hs.*;
import ru.bmn.web.hsdb.model.repository.hs.CardRepository;
import ru.bmn.web.hsdb.model.repository.hs.SoundRepository;
import ru.bmn.web.hsdb.parser.hearthpwn.Site;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HearthpwnDatabaseImport {
	private static final Logger LOG = LogManager.getLogger(HearthpwnDatabaseImport.class);

	@Autowired
	private EntityFactory entityFactory;
	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private SoundRepository soundRepository;

	public void run()
		throws IOException
	{
		LOG.info("Import process run...");
		List<Card> allCards = new Site().getAllCards();

		LOG.info("Import {} cards...", allCards.size());
		for (Card newCard : allCards) {
			Card currentCard = this.cardRepository.findFirstByName(newCard.getName());
			if (currentCard != null) {
				LOG.info("Card already exists: '{}' id={}", currentCard.getName(), currentCard.getId());
				newCard.setId(currentCard.getId());
			}
			else {
				LOG.info("New card: '{}'", newCard.getName());
			}

			this.updateCard(newCard);
		}
	}

	private void updateCard(Card card) {
		card.setArtist(
			(Artist) this.entityFactory.getPersistEntity(card.getArtist())
		);

		card.setRace(
			card.getRace() != null
				? (Race) this.entityFactory.getPersistEntity(card.getRace())
				: null
		);

		card.setRarity(
			(Rarity) this.entityFactory.getPersistEntity(card.getRarity())
		);

		card.setSeries(
			(Series) this.entityFactory.getPersistEntity(card.getSeries())
		);

		card.setType(
			(Type) this.entityFactory.getPersistEntity(card.getType())
		);

		// Many to many

		if (card.getMechanic() != null && !card.getMechanic().isEmpty()) {
			Set<Mechanic> persistMechanics = new HashSet<>();
			for (Mechanic mechanic : card.getMechanic()) {
				persistMechanics.add(
					(Mechanic) this.entityFactory.getPersistEntity(mechanic)
				);
			}
			card.setMechanic(persistMechanics);
		}

		if (card.getCharacters() != null && !card.getCharacters().isEmpty()) {
			Set<CharacterClass> persistChars = new HashSet<>();
			for (CharacterClass character : card.getCharacters()) {
				persistChars.add(
					(CharacterClass) this.entityFactory.getPersistEntity(character)
				);
			}
			card.setCharacters(persistChars);
		}

		if (card.getId() != null) {
			this.soundRepository.deleteByCardId(card.getId());
		}

		this.cardRepository.save(card);
	}
}
