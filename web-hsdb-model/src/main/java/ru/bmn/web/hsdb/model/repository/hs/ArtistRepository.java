package ru.bmn.web.hsdb.model.repository.hs;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.hs.Artist;
import ru.bmn.web.hsdb.model.repository.hs.common.EntityWithUniqueNameRepository;


public interface ArtistRepository extends CrudRepository<Artist, Integer>, EntityWithUniqueNameRepository<Artist> {
}
