package ru.bmn.web.hsdb.model.entity.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class UserRole {
	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	public Integer getId() {
		return id;
	}

	public UserRole setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public UserRole setName(String name) {
		this.name = name;
		return this;
	}

	public Set<User> getUsers() {
		return users;
	}

	public UserRole setUsers(Set<User> users) {
		this.users = users;
		return this;
	}
}
