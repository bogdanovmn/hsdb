package com.github.bogdanovmn.hsdb.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CollectionItemRepository extends JpaRepository<CollectionItem, Integer> {
	Long removeAllByUserId(int userId);
}
