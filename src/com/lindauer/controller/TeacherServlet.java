package com.lindauer.controller;

import com.lindauer.dao.ClassroomDao;
import com.lindauer.model.Classroom;
import com.lindauer.model.Teacher;
import com.lindauer.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author clindauer
 * @since 10/21/14
 */
@WebServlet(
        description = "Teacher Servlet",
        urlPatterns = {"/TeacherServlet"}
)
public class TeacherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            try {
                List<Classroom> classList = ClassroomDao.getClassroomsForTeacher((Teacher)user);
                request.setAttribute("classList", classList);
                request.getRequestDispatcher("teacherWelcome.jsp").forward(request, response);
            }
            catch (SQLException e) {
                throw new ServletException(e);
            }
        }
        else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
