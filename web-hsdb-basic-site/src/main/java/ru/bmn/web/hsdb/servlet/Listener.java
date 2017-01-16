package ru.bmn.web.hsdb.servlet;

import ru.bmn.web.hsdb.domain.BoosterCards;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Listener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent event) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gb_hsdb2?useSSL=false", "root", "12345");
			event.getServletContext().setAttribute("dbConnection", connection);
			// Prefetch all cards info			
			event.getServletContext().setAttribute("boosterCards", new BoosterCards(connection));
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
