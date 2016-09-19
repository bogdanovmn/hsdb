package ru.bmn.web.hsdb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.bmn.web.hsdb.UserRegister;

import java.io.IOException;
import java.sql.Connection;

public class Login extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		
		if (name != null && pass != null) {
			UserRegister userRegister = new UserRegister(
				(Connection)this.getServletContext().getAttribute("dbConnection"),
				name,
				pass
			);
			
			if (userRegister.exists()) {
				req.getSession().setAttribute("userId", userRegister.getId());
				resp.sendRedirect("/collection/out/");
				return;
			}
		}
		getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
	}
}
