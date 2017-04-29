package ru.bmn.web.hsdb.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.Sound;
import ru.bmn.web.hsdb.model.entity.Type;

public interface SoundRepository extends CrudRepository<Sound, Integer> {
}
