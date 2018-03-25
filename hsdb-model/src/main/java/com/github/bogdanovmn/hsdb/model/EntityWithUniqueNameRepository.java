package com.github.bogdanovmn.hsdb.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityWithUniqueNameRepository<EntityT extends EntityWithUniqueName>
	extends JpaRepository<EntityT, Integer>
{
	EntityT findFirstByName(String name);
}
