package ru.bmn.web.hsdb.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.hs.Sound;

public interface SoundRepository extends CrudRepository<Sound, Integer> {
}
