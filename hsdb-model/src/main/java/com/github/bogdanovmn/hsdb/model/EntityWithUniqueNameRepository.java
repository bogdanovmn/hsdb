package com.github.bogdanovmn.hsdb.model;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityWithUniqueNameRepository<EntityT extends EntityWithUniqueName>
	extends CrudRepository<EntityT, Integer>
{
	EntityT findFirstByName(String name);
}
