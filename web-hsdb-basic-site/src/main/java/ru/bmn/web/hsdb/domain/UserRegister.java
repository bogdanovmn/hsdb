package ru.bmn.web.hsdb.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegister {
	final private Connection connection;
	final private String email;
	final private String pass;
	private int id = -1;
	
	public UserRegister(Connection connection, String email, String pass) {
		this.connection = connection;
		this.email      = email;
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
					"SELECT id FROM users WHERE email = ? AND password = MD5(?)"
				);
				stmt.setString(1, this.email);
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

	public void create(String name, String ip) {
		try {
			PreparedStatement stmt = this.connection.prepareStatement(
				"INSERT INTO users SET email = ?, password = MD5(?), name = ?, ip = ?"
			);

			stmt.setString(1, this.email);
			stmt.setString(2, this.pass);
			stmt.setString(3, name);
			stmt.setString(4, ip);

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
