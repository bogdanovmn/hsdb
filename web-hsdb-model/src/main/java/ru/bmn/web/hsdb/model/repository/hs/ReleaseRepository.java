package ru.bmn.web.hsdb.model.repository.hs;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.hs.Series;

public interface ReleaseRepository extends CrudRepository<Series, Integer> {
}
