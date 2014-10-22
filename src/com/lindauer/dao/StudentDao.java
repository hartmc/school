package com.lindauer.dao;

import com.lindauer.model.Student;
import com.lindauer.util.DbUtility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author clindauer
 * @since 10/21/14
 */
public class StudentDao {
    private static Map<Integer,Student> idToStudent = new HashMap<>();

    /**
     * do not instantiate
     */
    private StudentDao() {}

    public static Student getStudentByID(int id) throws IOException, SQLException {
        Student result = idToStudent.get(id);
        if(result == null) {
            String studentSQL = "select a.username, a.password, a.first, a.last from users a, students b where a.userid=? && a.userid = b.userid";
            Connection connection = DbUtility.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(studentSQL)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.next())
                        return null;
                    result = new Student(id,
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("first"),
                            resultSet.getString("last"));
                    idToStudent.put(id, result);
                }
            } finally {
                DbUtility.returnConnection(connection);
            }
        }

        return result;
    }

}
