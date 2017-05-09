package ru.bmn.web.hsdb.model.entity.app;

import ru.bmn.web.hsdb.model.entity.common.EntityWithUniqueName;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class User extends EntityWithUniqueName {
	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String passwordHash;

	private String hearthpwnUserName;

	@Column(nullable = false)
	private Date registerDate;

	@OneToMany(mappedBy = "user")
	private Set<CollectionItem> collection;

	@ManyToMany
	@JoinTable(
		name = "role2user",
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
	)
	private Set<UserRole> roles;

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public User setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
		return this;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public User setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
		return this;
	}

	public Set<CollectionItem> getCollection() {
		return collection;
	}

	public User setCollection(Set<CollectionItem> collection) {
		this.collection = collection;
		return this;
	}

	public String getHearthpwnUserName() {
		return hearthpwnUserName;
	}

	public User setHearthpwnUserName(String hearthpwnUserName) {
		this.hearthpwnUserName = hearthpwnUserName;
		return this;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public User setRoles(Set<UserRole> roles) {
		this.roles = roles;
		return this;
	}
}
