package ru.bmn.web.hsdb.etl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bmn.web.hsdb.model.entity.EntityFactory;
import ru.bmn.web.hsdb.model.entity.app.CollectionItem;
import ru.bmn.web.hsdb.model.entity.app.User;
import ru.bmn.web.hsdb.model.repository.app.CollectionItemRepository;
import ru.bmn.web.hsdb.model.repository.app.UserRepository;
import ru.bmn.web.hsdb.parser.hearthpwn.Site;

import java.io.IOException;
import java.util.Set;

public class HearthpwnCollectionImport implements ImportService {
	private static final Logger LOG = LogManager.getLogger(HearthpwnCollectionImport.class);

	@Autowired
	private CollectionItemRepository collectionItemRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EntityFactory entityFactory;


	public void run()
		throws IOException
	{
		LOG.info("Import process run...");

		Set<User> usersToSync = this.userRepository.findAllByHearthpwnUserNameIsNotNull();
		LOG.info("Total users to sync: {}", usersToSync.size());

		for (User user : usersToSync) {
			this.syncCollection(user);
		}
	}

	private void syncCollection(User user)
		throws IOException
	{
		LOG.info("Parse collection for user '{}'...", user.getHearthpwnUserName());
		Set<CollectionItem> collection = new Site().getUserCards(user.getHearthpwnUserName());

		LOG.info("Drop current collection...");
		this.collectionItemRepository.removeAllByUserId(user.getId());
		LOG.info("Import collection of {} cards...", collection.size());
		for (CollectionItem item : collection) {
			item.setCard(
				this.entityFactory.getCardByName(
					item.getCard().getName()
				)
			);
			item.setUser(user);

			if (item.getCard() == null) {
				throw new RuntimeException("Unknown card: " + item.getCard().getName());
			}
		}

		this.collectionItemRepository.save(collection);
	}

}
