package ru.bmn.web.hsdb.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class Listener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent event) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gb_hsdb2", "root", "pass");
			event.getServletContext().setAttribute("dbConnection", connection);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
