package ru.bmn.web.hsdb.model.entity.hs;

import ru.bmn.web.hsdb.model.entity.hs.common.EntityWithUniqueName;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Card extends EntityWithUniqueName {
//	@Id
//	@GeneratedValue
//	private Integer id;

	@Column(nullable = false)
	private int manaCost;

	@Column(nullable = false)
	private boolean collectible;

	private int attack;
	private int health;
	private String text;
	private String aboutText;
	private String externalUrl;

	@Column(nullable = false)
	private String imageUrl;
	private String goldImageUrl;


	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "type_id")
	private Type type;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "race_id")
	private Race race;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "series_id")
	private Series series;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "rarity_id")
	private Rarity rarity;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "artist_id")
	private Artist artist;

	@OneToMany
	@JoinColumn(name = "card_id")
	private Set<Sound> sounds;

	@ManyToMany
	@JoinTable(
		name = "card2class",
		joinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "class_id", referencedColumnName = "id")
	)
	private Set<CharacterClass> characters;

	@ManyToMany
	@JoinTable(
		name = "card2mechanic",
		joinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "mechanic_id", referencedColumnName = "id")
	)
	private Set<Mechanic> mechanic;


	public int getManaCost() {
		return manaCost;
	}

	public Card setManaCost(int manaCost) {
		this.manaCost = manaCost;
		return this;
	}

	public int getAttack() {
		return attack;
	}

	public Card setAttack(int attack) {
		this.attack = attack;
		return this;
	}

	public int getHealth() {
		return health;
	}

	public Card setHealth(int health) {
		this.health = health;
		return this;
	}

	public String getText() {
		return text;
	}

	public Card setText(String text) {
		this.text = text;
		return this;
	}

	public String getAboutText() {
		return aboutText;
	}

	public Card setAboutText(String aboutText) {
		this.aboutText = aboutText;
		return this;
	}

	public boolean isCollectible() {
		return collectible;
	}

	public Card setCollectible(boolean collectible) {
		this.collectible = collectible;
		return this;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Card setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public String getGoldImageUrl() {
		return goldImageUrl;
	}

	public Card setGoldImageUrl(String goldImageUrl) {
		this.goldImageUrl = goldImageUrl;
		return this;
	}

	public Type getType() {
		return type;
	}

	public Card setType(Type type) {
		this.type = type;
		return this;
	}

	public Race getRace() {
		return race;
	}

	public Card setRace(Race race) {
		this.race = race;
		return this;
	}

	public Series getSeries() {
		return series;
	}

	public Card setSeries(Series series) {
		this.series = series;
		return this;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public Card setRarity(Rarity rarity) {
		this.rarity = rarity;
		return this;
	}

	public Artist getArtist() {
		return artist;
	}

	public Card setArtist(Artist artist) {
		this.artist = artist;
		return this;
	}

	public Set<Sound> getSounds() {
		return sounds;
	}

	public Card setSounds(Set<Sound> sounds) {
		this.sounds = sounds;
		return this;
	}

	public Set<Mechanic> getMechanic() {
		return mechanic;
	}

	public Card setMechanic(Set<Mechanic> mechanic) {
		this.mechanic = mechanic;
		return this;
	}

	public Set<CharacterClass> getCharacters() {
		return characters;
	}

	public Card setCharacters(Set<CharacterClass> characters) {
		this.characters = characters;
		return this;
	}

	public String getExternalUrl() {
		return externalUrl;
	}

	public Card setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
		return this;
	}
}
