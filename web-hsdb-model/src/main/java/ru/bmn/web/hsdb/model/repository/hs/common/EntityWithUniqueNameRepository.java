package ru.bmn.web.hsdb.model.repository.hs.common;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityWithUniqueNameRepository<EntityT>
	extends CrudRepository<EntityT, Integer>
{
	EntityT findFirstByName(String name);
}
