package ru.bmn.web.hsdb.model.repository.hs;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.hs.Card;


public interface CardRepository extends CrudRepository<Card, Integer> {
}
