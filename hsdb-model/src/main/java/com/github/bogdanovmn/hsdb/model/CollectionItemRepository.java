package com.github.bogdanovmn.hsdb.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CollectionItemRepository extends CrudRepository<CollectionItem, Integer> {
	Long removeAllByUserId(int userId);
}
