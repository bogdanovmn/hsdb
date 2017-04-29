package ru.bmn.web.hsdb.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.Artist;
import ru.bmn.web.hsdb.model.entity.Rarity;

public interface RarityRepository extends CrudRepository<Rarity, Integer> {
}
