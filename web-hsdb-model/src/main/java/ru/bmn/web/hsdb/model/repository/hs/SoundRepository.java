package ru.bmn.web.hsdb.model.repository.hs;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.bmn.web.hsdb.model.entity.hs.Sound;

public interface SoundRepository extends CrudRepository<Sound, Integer> {
	@Transactional
	Long deleteByCardId(Integer id);
}
