package ru.bmn.web.hsdb.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserCollection {
	private final Connection connection;
	private final int userId;
	private boolean isAlreadyFetched = false;
	
	public UserCollection(Connection connection, int userId) {
		this.connection = connection;
		this.userId     = userId;
	}
	
	private void fetch() {
		if (!this.isAlreadyFetched) {
			try {
				PreparedStatement stmt = this.connection.prepareStatement(
					"SELECT * FROM collections WHERE user_id = ?"
				);
				stmt.setInt(1, this.userId);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					int card_id = rs.getInt("card_id");
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
