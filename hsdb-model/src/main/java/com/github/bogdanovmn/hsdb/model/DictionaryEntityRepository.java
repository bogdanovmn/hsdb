package com.github.bogdanovmn.hsdb.model;


import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DictionaryEntityRepository<EntityT extends DictionaryEntity>
	extends EntityWithUniqueNameRepository<EntityT> {
}
