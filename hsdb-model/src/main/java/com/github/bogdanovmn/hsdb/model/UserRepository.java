package com.github.bogdanovmn.hsdb.model;

import java.util.Set;

public interface UserRepository extends EntityWithUniqueNameRepository<User> {
	Set<User> findAllByHearthpwnUserNameIsNotNull();
}
