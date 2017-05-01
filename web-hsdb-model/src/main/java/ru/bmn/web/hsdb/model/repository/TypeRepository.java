package ru.bmn.web.hsdb.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.hs.Type;

public interface TypeRepository extends CrudRepository<Type, Integer> {
}
