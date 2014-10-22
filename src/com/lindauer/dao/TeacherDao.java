package com.lindauer.dao;

import com.lindauer.model.Teacher;
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
public class TeacherDao {
    private static Map<Integer, Teacher> idToTeacher = new HashMap<>();

    /**
     * do not instantiate
     */
    private TeacherDao() {}

    public static Teacher getTeacherByID(int id) throws IOException, SQLException {
        Teacher result = idToTeacher.get(id);
        if(result == null) {
            String teacherSQL = "select a.username, a.password, a.first, a.last from users a, teachers b where a.userid=? && a.userid = b.userid";
            Connection connection = DbUtility.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(teacherSQL)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.next())
                        return null;

                    result = new Teacher(id,
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("first"),
                            resultSet.getString("last"));
                    idToTeacher.put(id, result);
                }
            } finally {
                DbUtility.returnConnection(connection);
            }
        }

        return result;
    }
}
