package ru.bmn.web.hsdb.etl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bmn.web.hsdb.model.entity.hs.*;
import ru.bmn.web.hsdb.model.repository.hs.CardRepository;
import ru.bmn.web.hsdb.parser.hearthpwn.Site;

import java.io.IOException;
import java.util.List;

public class HearthpwnDatabaseImport {
	private static final Logger LOG = LogManager.getLogger(HearthpwnDatabaseImport.class);

	@Autowired
	private EntityFactory entityFactory;
	@Autowired
	private CardRepository cardRepository;

	public void run()
		throws IOException
	{
		LOG.info("Import process run...");
		List<Card> allCards = new Site().getAllCards();

		LOG.info("Import {} cards...", allCards.size());
		for (Card newCard : allCards) {
			Card currentCard = this.cardRepository.findFirstByName(newCard.getName());
			if (currentCard != null) {
				newCard.setId(currentCard.getId());
			}

			newCard.setArtist(
				(Artist) this.entityFactory.getEntityByName(Artist.class, newCard.getArtist().getName())
			);

			newCard.setRace(
				newCard.getRace() != null
					? (Race) this.entityFactory.getEntityByName(Race.class, newCard.getRace().getName())
					: null
			);

			newCard.setRarity(
				(Rarity) this.entityFactory.getEntityByName(Rarity.class, newCard.getRarity().getName())
			);

			newCard.setSeries(
				(Series) this.entityFactory.getEntityByName(Series.class, newCard.getSeries().getName())
			);

			newCard.setType(
				(Type) this.entityFactory.getEntityByName(Type.class, newCard.getType().getName())
			);

			this.cardRepository.save(newCard);
		}
	}
}
