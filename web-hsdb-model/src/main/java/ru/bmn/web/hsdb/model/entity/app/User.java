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

	private String name;
	private String email;
	private String passwordHash;
	private Date registerDate;

	@OneToMany(mappedBy = "user")
	private Set<CollectionItem> collection;
}
