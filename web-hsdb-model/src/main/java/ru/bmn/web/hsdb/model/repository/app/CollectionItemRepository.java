package ru.bmn.web.hsdb.model.repository.app;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.app.CollectionItem;

public interface CollectionItemRepository extends CrudRepository<CollectionItem, Integer> {
}
