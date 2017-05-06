package ru.bmn.web.hsdb.model.repository.hs;

import org.springframework.transaction.annotation.Transactional;
import ru.bmn.web.hsdb.model.entity.hs.Card;
import ru.bmn.web.hsdb.model.repository.hs.common.EntityWithUniqueNameRepository;

@Transactional
public interface CardRepository extends EntityWithUniqueNameRepository<Card> {
	Card findFirstByName(String name);
}
