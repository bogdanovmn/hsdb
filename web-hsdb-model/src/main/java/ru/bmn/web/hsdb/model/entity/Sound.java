package ru.bmn.web.hsdb.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Sound {
	@Id
	private int id;

	private String name;
	private String url;

	@ManyToOne
	@JoinColumn(name = "card_id")
	private Card card;
}
