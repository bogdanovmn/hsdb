package ru.bmn.web.hsdb.model.repository.common;


import org.springframework.data.repository.NoRepositoryBean;
import ru.bmn.web.hsdb.model.entity.common.DictionaryEntity;

@NoRepositoryBean
public interface DictionaryEntityRepository<EntityT extends DictionaryEntity>
	extends EntityWithUniqueNameRepository<EntityT> {
}
