package com.lindauer.controller;

import com.lindauer.dao.UserDao;
import com.lindauer.model.User;

import javax.security.sasl.AuthenticationException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author clindauer
 * @since 10/20/14
 */
@WebServlet(
        description = "Login Servlet",
        urlPatterns = {"/LoginServlet"}
)
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = UserDao.validate(username, password);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            authenticationSuccess(request, response);
        }
        catch (AuthenticationException ae) {
            authenticationFailed("Sorry, username or password invalid",
                    request, response, out);
        }
        catch (Exception e) {
            authenticationFailed("Oops! There was a problem with the database. Please try again later.  Sorry!",
                    request, response, out);
        }
    }

    private void authenticationSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.html");
        requestDispatcher.forward(request, response);
    }

    private void authenticationFailed(String message, HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, IOException {
        out.print("<p style=\"color:red\">" + message + "</p>");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.include(request, response);
    }
}
