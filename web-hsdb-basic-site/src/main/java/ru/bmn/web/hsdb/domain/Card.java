package ru.bmn.web.hsdb.domain;

import java.util.Properties;

public class Card {
	private final Properties props;
	private final int id;
	private final int rarityId;
	private final int setId;
	private final int manaCost;
	private final int characterId;

	public Card(Properties props) {
		this.props = props;
		this.id = Integer.valueOf(props.getProperty("id"));
		this.rarityId = Integer.valueOf(props.getProperty("rarity_id"));
		this.setId = Integer.valueOf(props.getProperty("set_id"));
		this.manaCost = Integer.valueOf(props.getProperty("mana_cost"));
		this.characterId = Integer.valueOf(props.getProperty("character_id"));
	}
	
	public boolean isLegend() {
		return this.rarityId == 5;
	}

	public int getId() {
		return this.id;
	}

	public int getManaCost() {
		return this.manaCost;
	}

	public int getRarityId() {
		return this.rarityId;
	}

	public int getSetId() {
		return this.setId;
	}

	public int getCharacterId() {
		return this.characterId;
	}
}
