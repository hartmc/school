package com.lindauer.dao;

import com.lindauer.model.Classroom;
import com.lindauer.model.Student;
import com.lindauer.model.Teacher;
import com.lindauer.util.DbUtility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author clindauer
 * @since 10/21/14
 */
public class ClassroomDao {
    private static Map<Integer,Classroom> idToClassroom = new HashMap<>();
    private static Map<Integer,List<Classroom>> teacherIdToClassrooms = new HashMap<>();
    private static Map<Integer,List<Classroom>> studentIdToClassrooms = new HashMap<>();

    /**
     * do not instantiate
     */
    private ClassroomDao() {}

    public static Classroom getClassroom(int roomId) throws IOException, SQLException {
        Classroom result = idToClassroom.get(roomId);
        if(result == null) {
            List<Student> students = getStudentsInClassroomFromDB(roomId);
            String classroomSql = "select name, teacher from classroom where id=?";

            Connection connection = DbUtility.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(classroomSql)) {
                preparedStatement.setInt(1, roomId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.next())
                        return null;
                    int teacherID = resultSet.getInt("teacher");
                    String name = resultSet.getString("name");
                    Teacher teacher = TeacherDao.getTeacherByID(teacherID);
                    result = new Classroom(roomId, name, teacher, students);
                    idToClassroom.put(roomId, result);
                }
            } finally {
                DbUtility.returnConnection(connection);
            }
        }

        return result;
    }

    private static List<Student> getStudentsInClassroomFromDB(int roomId) throws IOException, SQLException {
        String studentSQL = "select a.userid, a.username, a.password, a.first, a.last from users a, enrollment b where b.roomID=? && a.userid=b.student";

        Connection connection = DbUtility.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(studentSQL)) {
            preparedStatement.setInt(1, roomId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Student> students = new ArrayList<>();
                while (resultSet.next()) {
                    Student student = new Student(resultSet.getInt("userid"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("first"),
                            resultSet.getString("last"));
                    students.add(student);
                }

                return students;
            }
        } finally {
            DbUtility.returnConnection(connection);
        }
    }

    public static List<Classroom> getClassroomsForTeacher(Teacher teacher) throws IOException, SQLException{
        List<Classroom> result = teacherIdToClassrooms.get(teacher.getId());
        if(result == null) {
            String teacherSQL = "select id, name from classroom where teacher=?";
            Connection connection = DbUtility.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(teacherSQL)) {
                preparedStatement.setInt(1, teacher.getId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    result = new ArrayList<>();
                    while (resultSet.next()) {
                        Classroom classroom = getClassroom(resultSet.getInt("id"));
                        result.add(classroom);
                    }

                    teacherIdToClassrooms.put(teacher.getId(), result);
                }
            } finally {
                DbUtility.returnConnection(connection);
            }
        }

        return result;
    }

    public static List<Classroom> getClassroomsForStudent(Student student) throws IOException, SQLException {
        List<Classroom> result = studentIdToClassrooms.get(student.getId());
        if(result == null) {
            String studentSQL = "select roomID from enrollment where student=? ";
            Connection connection = DbUtility.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(studentSQL)) {
                preparedStatement.setInt(1, student.getId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    result = new ArrayList<>();
                    while (resultSet.next()) {
                        Classroom classroom = getClassroom(resultSet.getInt("roomID"));
                        result.add(classroom);
                    }
                    studentIdToClassrooms.put(student.getId(), result);
                }
            }
            finally {
                DbUtility.returnConnection(connection);
            }
        }

        return result;
    }
}
