package com.github.bogdanovmn.hsdb.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SoundRepository extends CrudRepository<Sound, Integer> {
	@Transactional
	Long deleteByCardId(Integer id);
}
