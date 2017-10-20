package com.github.bogdanovmn.hsdb.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class UserRole extends EntityWithUniqueName{
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;


	public Set<User> getUsers() {
		return users;
	}

	public UserRole setUsers(Set<User> users) {
		this.users = users;
		return this;
	}
}
