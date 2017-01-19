package ru.bmn.web.hsdb.domain;

import ru.bmn.web.hsdb.common.SqlResultSetMap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class BoosterCards {
	private final Connection connection;
	private final Map<Integer, Card>       cardsById          = new HashMap<>();
	private final Map<Integer, List<Card>> cardsByCharacterId = new HashMap<>();
	
	private boolean isAlreadyFetched = false;

	
	public BoosterCards(final Connection connection) {
		this.connection = connection;
	}

	public Card cardById(final int id) {
		this.fetch();
		return this.cardsById.get(id);
	}
	
	private void fetch() {
		if (!this.isAlreadyFetched) {
			try {
				Statement stmt = this.connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM cards WHERE set_id IN (2, 7, 9)");

				while (rs.next()) {
					Properties props = new SqlResultSetMap(rs).toProperties();
//					props.setProperty("id"          , rs.getString("id"));
//					props.setProperty("name"        , rs.getString("name"));
//					props.setProperty("mana_cost"   , rs.getString("mana_cost"));
//					props.setProperty("attack"      , rs.getString("attack"));
//					props.setProperty("life"        , rs.getString("life"));
//					props.setProperty("descr"       , rs.getString("descr"));
//					props.setProperty("image_url"   , rs.getString("image_url"));
//					props.setProperty("character_id", rs.getString("character_id") == null ? "0" : rs.getString("character_id"));
//					props.setProperty("rarity_id"   , rs.getString("rarity_id"));
//					props.setProperty("type_id"     , rs.getString("type_id"));
//					props.setProperty("race_id"     , rs.getString("race_id"));
//					props.setProperty("set_id"      , rs.getString("set_id"));
//					props.setProperty("collectible" , rs.getString("collectible"));
//					props.setProperty("ext_id"      , rs.getString("ext_id"));
//					props.setProperty("hh_rating"   , rs.getString("hh_rating"));

					Card card = new Card(props);
					int characterId = Integer.valueOf(props.getProperty("character_id"));

					this.cardsById.put(Integer.valueOf(props.getProperty("id")), card);
					
					if (this.cardsByCharacterId.containsKey(characterId)) {
						this.cardsByCharacterId
							.get(characterId)
							.add(card);
					}
					else {
						this.cardsByCharacterId
							.put(
								characterId,
								new ArrayList<Card>()
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
		return this.cardsById.size();
	}
}
