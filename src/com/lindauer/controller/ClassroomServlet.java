package com.lindauer.controller;

import com.lindauer.dao.ClassroomDao;
import com.lindauer.model.Classroom;
import com.lindauer.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author clindauer
 * @since 10/21/14
 */
public class ClassroomServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roomId = Integer.parseInt(req.getParameter("id"));
        try {
            Classroom classroom = ClassroomDao.getClassroom(roomId);
            req.setAttribute("classroom", classroom);
            List<Student> studentList = classroom.getStudents();
            req.setAttribute("studentList", studentList);
            req.getRequestDispatcher("classroom.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
