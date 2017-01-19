package ru.bmn.web.hsdb.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserCollection {
	private final Connection connection;
	private final int userId;
	private boolean isFetched = false;
	private List<CollectionItem> items = new ArrayList<>();
	
	public UserCollection(Connection connection, int userId) {
		this.connection = connection;
		this.userId     = userId;
	}
	
	private void fetch() {
		if (!this.isFetched) {
			try {
				PreparedStatement stmt = this.connection.prepareStatement(
					"SELECT * FROM collections WHERE user_id = ?"
				);
				stmt.setInt(1, this.userId);

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					this.items.add(
						new CollectionItem(
							rs.getInt("card_id"),
							rs.getInt("norm_count"),
							rs.getInt("gold_count")
						)
					);
				}
				rs.close();
				stmt.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				this.isFetched = true;
			}
		}
	}

	public int total() {
		this.fetch();
		return items.size();
	}

	public List<CollectionItem> getItems() {
		this.fetch();
		return this.items;
	}
}
