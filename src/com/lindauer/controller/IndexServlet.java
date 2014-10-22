package com.lindauer.controller;

import com.lindauer.model.Student;
import com.lindauer.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author clindauer
 * @since 10/20/14
 */
@WebServlet(
        description = "Index Servlet",
        urlPatterns = {"/IndexServlet"}
)
public class IndexServlet extends HttpServlet {

    public static String getSchoolName() {
        return "Lindauer Elementary School";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user instanceof Student) {
                request.setAttribute("student", user);
                request.getRequestDispatcher("student.html").forward(request, response);
            }
            else {
                request.setAttribute("teacher", user);
                request.getRequestDispatcher("teacher.html").forward(request, response);
            }
        }
        else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
