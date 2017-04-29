package ru.bmn.web.hsdb.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.Release;
import ru.bmn.web.hsdb.model.entity.Type;

public interface TypeRepository extends CrudRepository<Type, Integer> {
}
