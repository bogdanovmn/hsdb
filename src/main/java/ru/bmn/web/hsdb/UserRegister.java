package ru.bmn.web.hsdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegister {
	final private Connection connection;
	final private String name;
	final private String pass;
	private int id = -1;
	
	public UserRegister(Connection connection, String name, String pass) {
		this.connection = connection;
		this.name       = name;
		this.pass       = pass;
	}
	
	public boolean exists() {
		this.fetch();
		return this.id > 0;
	}
	
	public int getId() {
		this.fetch(); 
		return this.id; 
	}
	
	private void fetch() {
		if (this.id == -1) {
			try {
				PreparedStatement stmt = this.connection.prepareStatement(
					"SELECT * FROM user WHERE name = ? AND password = ?"
				);
				stmt.setString(1, this.name);
				stmt.setString(2, this.pass);
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next()) {
					this.id = rs.getInt("id");
				}
				else {
					this.id = 0;
				}
				
				rs.close();
				stmt.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
