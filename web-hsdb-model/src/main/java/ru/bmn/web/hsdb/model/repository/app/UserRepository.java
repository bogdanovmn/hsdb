package ru.bmn.web.hsdb.model.repository.app;

import ru.bmn.web.hsdb.model.entity.app.User;
import ru.bmn.web.hsdb.model.repository.common.EntityWithUniqueNameRepository;

import java.util.Set;

public interface UserRepository extends EntityWithUniqueNameRepository<User> {
	Set<User> findAllByHearthpwnUserNameIsNotNull();
}
