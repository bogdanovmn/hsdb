package com.github.bogdanovmn.hsdb.model;

import javax.persistence.*;

@Entity
public class Sound {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String url;

	@ManyToOne
	private Card card;

	public Integer getId() {
		return id;
	}

	public Sound setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Sound setName(String name) {
		this.name = name;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public Sound setUrl(String url) {
		this.url = url;
		return this;
	}

	public Card getCard() {
		return card;
	}

	public Sound setCard(Card card) {
		this.card = card;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Sound sound = (Sound) o;

		return
			getName().equals(sound.getName())
				&& getUrl().equals(sound.getUrl());
	}

	@Override
	public int hashCode() {
		int result = getName().hashCode();
		result = 31 * result + getUrl().hashCode();
		return result;
	}
}
