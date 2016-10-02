package ru.bmn.web.hsdb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import ru.bmn.web.hsdb.Card;

public class BoosterCards {
	private final Connection connection;
	private HashMap<String, Card>       cardsById          = null;
	private HashMap<String, List<Card>> cardsByCharacterId = null;
	
	private boolean isAlreadyFetched = false;

	
	public BoosterCards(Connection connection) {
		this.connection = connection;
	}

	public Card cardById(int id) {
		this.fetch();
		return this.cardsById.get(id);
	}
	
	private void fetch() {
		if (!this.isAlreadyFetched) {
			try {
				Statement stmt = this.connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM cards WHERE set_id IN (2, 7, 9)");

				while (rs.next()) {
					Properties props = new Properties();
					props.setProperty("id"          , rs.getString("id"));
					props.setProperty("name"        , rs.getString("name"));
					props.setProperty("mana_cost"   , rs.getString("mana_cost"));
					props.setProperty("attack"      , rs.getString("attack"));
					props.setProperty("life"        , rs.getString("life"));
					props.setProperty("descr"       , rs.getString("descr"));
					props.setProperty("image_url"   , rs.getString("image_url"));
					props.setProperty("character_id", rs.getString("character_id") == null ? "0" : rs.getString("character_id"));
					props.setProperty("rarity_id"   , rs.getString("rarity_id"));
					props.setProperty("type_id"     , rs.getString("type_id"));
					props.setProperty("race_id"     , rs.getString("race_id"));
					props.setProperty("set_id"      , rs.getString("set_id"));
					props.setProperty("collectible" , rs.getString("collectible"));
					props.setProperty("ext_id"      , rs.getString("ext_id"));
					props.setProperty("hh_rating"   , rs.getString("hh_rating"));

					Card card = new Card(props);
					
					this.cardsById.put(props.getProperty("id"), card);
					
					if (this.cardsByCharacterId.containsKey(props.getProperty("character_id"))) {
						this.cardsByCharacterId
							.get(props.getProperty("character_id"))
							.add(card);
					}
					else {
						this.cardsByCharacterId
							.put(
								props.getProperty("character_id"), 
								new ArrayList<Card>(100)
							).add(card);
					}
				}
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				this.isAlreadyFetched = true;
			}
		}
	}

	public int total() {
		// TODO Auto-generated method stub
		return 0;
	}
}
