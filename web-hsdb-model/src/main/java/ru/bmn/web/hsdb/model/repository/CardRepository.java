package ru.bmn.web.hsdb.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.Card;


public interface CardRepository extends CrudRepository<Card, Integer> {
}
