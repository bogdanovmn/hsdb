package ru.bmn.web.hsdb.model.entity.app;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(
	uniqueConstraints = {
		@UniqueConstraint(
			columnNames = {"name"}
		)
	}
)
public class User {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String passwordHash;
	@Column(nullable = false)
	private Date registerDate;

	@OneToMany(mappedBy = "user")
	private Set<CollectionItem> collection;

	public Integer getId() {
		return id;
	}

	public User setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

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
}
