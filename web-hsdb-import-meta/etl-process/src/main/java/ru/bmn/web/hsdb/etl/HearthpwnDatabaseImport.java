package ru.bmn.web.hsdb.etl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bmn.web.hsdb.model.entity.hs.Card;
import ru.bmn.web.hsdb.model.repository.hs.CardRepository;
import ru.bmn.web.hsdb.parser.hearthpwn.Site;

import java.io.IOException;
import java.util.List;

public class HearthpwnDatabaseImport {
	private static final Logger LOG = LogManager.getLogger(HearthpwnDatabaseImport.class);

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
			this.cardRepository.save(newCard);
		}
	}
}
