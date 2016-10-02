package ru.bmn.web.hsdb.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ru.bmn.web.hsdb.BoosterCards;


public class Listener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent event) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gb_hsdb2?useSSL=false", "root", "pass");
			event.getServletContext().setAttribute("dbConnection", connection);
			// Prefetch all cards info			
			event.getServletContext().setAttribute("boosterCards", new BoosterCards(connection));
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
