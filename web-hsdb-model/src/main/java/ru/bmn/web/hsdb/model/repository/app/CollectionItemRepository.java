package ru.bmn.web.hsdb.model.repository.app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.bmn.web.hsdb.model.entity.app.CollectionItem;

@Transactional
public interface CollectionItemRepository extends CrudRepository<CollectionItem, Integer> {
	Long removeAllByUserId(int userId);
}
