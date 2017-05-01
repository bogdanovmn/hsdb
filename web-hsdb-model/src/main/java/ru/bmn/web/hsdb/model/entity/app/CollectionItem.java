package ru.bmn.web.hsdb.model.entity.app;

import ru.bmn.web.hsdb.model.entity.hs.Card;

import javax.persistence.*;

@Entity
@Table(
	uniqueConstraints = {
		@UniqueConstraint(
			columnNames = {"user_id", "card_id"}
		)
	}
)
public class CollectionItem {
	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "card_id")
	private Card card;

	private Integer normalCount;
	private Integer goldCount;
}
