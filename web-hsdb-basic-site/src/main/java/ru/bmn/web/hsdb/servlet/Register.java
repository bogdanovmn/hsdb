package ru.bmn.web.hsdb.servlet;

import ru.bmn.web.hsdb.domain.UserRegister;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 */
public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String passCheck = request.getParameter("password_check");
        String zombiCheck = request.getParameter("zombi_check");

        String errorMsg = "";
        if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || passCheck.isEmpty() || zombiCheck.isEmpty()) {
            errorMsg = "Все поля должны быть заполнены";
        }
        else if (!pass.equals(passCheck)) {
            errorMsg = "Пароли не совпадают";
        }
        else if (!zombiCheck.equals("4")) {
            errorMsg = "Вы зомби?";
        }

        if (errorMsg.isEmpty()) {
            UserRegister newUser = new UserRegister(
                (Connection)this.getServletContext().getAttribute("dbConnection"),
                email,
                pass
            );

            if (!newUser.exists()) {
                newUser.create(name, request.getRemoteAddr());
                if (newUser.exists()) {
                    request.getSession().setAttribute("userId", newUser.getId());
                    response.sendRedirect("../collection/in/");
                    return;
                }
                else {
                    errorMsg = "Ошибка добавления нового пользователя :(";
                }
            }
        }

        request.setAttribute("errorMsg", errorMsg);
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }
}
