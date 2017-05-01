package ru.bmn.web.hsdb.model.entity.hs;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(
	uniqueConstraints = {
		@UniqueConstraint(
			columnNames = {"name"}
		)
	}
)
public class Card {
	@Id
	@GeneratedValue
	private Integer id;

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
	@JoinColumn(name = "character_id")
	private CharacterClass character;

	@OneToOne
	@JoinColumn(name = "type_id")
	private Type type;

	@OneToOne
	@JoinColumn(name = "race_id")
	private Race race;

	@OneToOne
	@JoinColumn(name = "series_id")
	private Series series;

	@OneToOne
	@JoinColumn(name = "rarity_id")
	private Rarity rarity;

	@OneToOne
	@JoinColumn(name = "artist_id")
	private Artist artist;

	@OneToMany(mappedBy = "card")
	private Set<Sound> sound;

	@ManyToMany(mappedBy = "cards")
	private Set<Mechanic> mechanic;


	public Integer getId() {
		return id;
	}

	public Card setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Card setName(String name) {
		this.name = name;
		return this;
	}

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

	public Set<Sound> getSound() {
		return sound;
	}

	public Card setSound(Set<Sound> sound) {
		this.sound = sound;
		return this;
	}
}
