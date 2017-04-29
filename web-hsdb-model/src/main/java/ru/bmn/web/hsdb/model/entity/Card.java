package ru.bmn.web.hsdb.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Card {
	@Id
	@GeneratedValue
	private int id;

	private String name;
	private int manaCost;
	private int attack;
	private int health;
	private String text;
	private String aboutText;
	private boolean collectible;

	private String imageUrl;
	private String goldImageUrl;

	@OneToOne
	@JoinColumn(name = "type_id")
	private Type type;

	@OneToOne
	@JoinColumn(name = "race_id")
	private Race race;

	@OneToOne
	@JoinColumn(name = "release_id")
	private Release release;

	@OneToOne
	@JoinColumn(name = "rarity_id")
	private Rarity rarity;

	@OneToOne
	@JoinColumn(name = "artist_id")
	private Artist artist;

	@OneToMany(mappedBy = "card")
	private Set<Sound> sound;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getManaCost() {
		return manaCost;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAboutText() {
		return aboutText;
	}

	public void setAboutText(String aboutText) {
		this.aboutText = aboutText;
	}

	public boolean isCollectible() {
		return collectible;
	}

	public void setCollectible(boolean collectible) {
		this.collectible = collectible;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getGoldImageUrl() {
		return goldImageUrl;
	}

	public void setGoldImageUrl(String goldImageUrl) {
		this.goldImageUrl = goldImageUrl;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public Release getRelease() {
		return release;
	}

	public void setRelease(Release release) {
		this.release = release;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Set<Sound> getSound() {
		return sound;
	}

	public void setSound(Set<Sound> sound) {
		this.sound = sound;
	}
}
