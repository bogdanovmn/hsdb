package ru.bmn.web.hsdb.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.hs.Rarity;

public interface RarityRepository extends CrudRepository<Rarity, Integer> {
}
