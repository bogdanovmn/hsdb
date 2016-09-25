package ru.bmn.web.hsdb;

import java.util.Properties;

public class Card {
	private Properties props;
	
	public Card(Properties props) {
		this.props = props;
	}
	
	public boolean isLegend() {
		return this.props.getProperty("rarity_id") == "5";
	}
}
