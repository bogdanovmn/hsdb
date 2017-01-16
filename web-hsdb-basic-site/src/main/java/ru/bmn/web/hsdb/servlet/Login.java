package ru.bmn.web.hsdb.servlet;

import ru.bmn.web.hsdb.domain.UserRegister;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class Login extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pass  = req.getParameter("password");

		if (!email.isEmpty() && !pass.isEmpty()) {
			UserRegister userRegister = new UserRegister(
				(Connection)this.getServletContext().getAttribute("dbConnection"),
				email,
				pass
			);
			
			if (userRegister.exists()) {
				req.getSession().setAttribute("userId", userRegister.getId());
				resp.sendRedirect("/collection/in/");
				return;
			}
			else {
				req.setAttribute("error", "Юзер не найден");
			}
		}
		else {
			req.setAttribute("error", "Заполните все поля!");
		}
		getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
	}
}
