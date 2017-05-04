package ru.bmn.web.hsdb.model.repository.hs;

import ru.bmn.web.hsdb.model.entity.hs.Card;
import ru.bmn.web.hsdb.model.repository.hs.common.EntityWithUniqueNameRepository;


public interface CardRepository extends EntityWithUniqueNameRepository<Card> {
	Card findFirstByName(String name);
}
