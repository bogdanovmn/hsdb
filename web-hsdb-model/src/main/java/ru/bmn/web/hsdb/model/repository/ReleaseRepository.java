package ru.bmn.web.hsdb.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.Rarity;
import ru.bmn.web.hsdb.model.entity.Release;

public interface ReleaseRepository extends CrudRepository<Release, Integer> {
}
