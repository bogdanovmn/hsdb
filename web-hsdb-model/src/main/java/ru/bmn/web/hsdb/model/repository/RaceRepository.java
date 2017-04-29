package ru.bmn.web.hsdb.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.Race;


public interface RaceRepository extends CrudRepository<Race, Integer> {
}
