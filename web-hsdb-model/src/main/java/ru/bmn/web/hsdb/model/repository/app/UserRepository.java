package ru.bmn.web.hsdb.model.repository.app;

import org.springframework.data.repository.CrudRepository;
import ru.bmn.web.hsdb.model.entity.app.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
