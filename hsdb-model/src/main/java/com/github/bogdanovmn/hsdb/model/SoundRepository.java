package com.github.bogdanovmn.hsdb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SoundRepository extends JpaRepository<Sound, Integer> {
	@Transactional
	Long deleteByCardId(Integer id);
}
