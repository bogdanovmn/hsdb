package ru.bmn.web.hsdb.model.repository.hs;

import org.springframework.transaction.annotation.Transactional;
import ru.bmn.web.hsdb.model.entity.hs.Mechanic;
import ru.bmn.web.hsdb.model.repository.hs.common.EntityWithUniqueNameRepository;

@Transactional
public interface MechanicRepository extends EntityWithUniqueNameRepository<Mechanic> {
}
