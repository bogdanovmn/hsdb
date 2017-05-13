package ru.bmn.web.hsdb.model.repository.common;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.bmn.web.hsdb.model.entity.common.DictionaryEntity;
import ru.bmn.web.hsdb.model.entity.common.EntityWithUniqueName;

@NoRepositoryBean
public interface DictionaryEntityRepository<EntityT extends DictionaryEntity>
	extends EntityWithUniqueNameRepository<EntityT> {
}
