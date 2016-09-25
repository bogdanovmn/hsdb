package ru.bmn.web.hsdb.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.bmn.web.hsdb.Cards;
import ru.bmn.web.hsdb.UserCollection;


public class Collection extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserCollection collection = new UserCollection(
			(Connection) this.getServletContext().getAttribute("dbConnection"), 
			(Integer)    req.getSession().getAttribute("user_id")
		);
		
		Cards cards = (Cards) this.getServletContext().getAttribute("cardsHelper");
		getServletContext().getRequestDispatcher("/collection.jsp").forward(req, resp);

	}
}