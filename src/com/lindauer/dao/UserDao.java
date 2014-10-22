package com.lindauer.dao;

import com.lindauer.model.Teacher;
import com.lindauer.model.User;
import com.lindauer.util.DbUtility;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author clindauer
 * @since 10/20/14
 */
public class UserDao {
    private static Map<String, String> nameToPassword = new HashMap<>();
    private static Map<String, Integer> namePlusPasswordToId = new HashMap<>();

    /**
     * do not instantiate
     */
    private UserDao() {}

    public static User validate (String name, String password) throws IOException, SQLException, AuthenticationException {
        String expectedPassword = nameToPassword.get(name);
        if(expectedPassword == null) {
            String SQL = "select userid from users where username=? and password=?";
            Connection connection = DbUtility.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL))  {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        /**
                         * Found username / password combination in the database.
                         * Cache this result as a valid username / password combo
                         */
                        expectedPassword = password;
                        nameToPassword.put(name, password);
                        int userId = resultSet.getInt("userid");
                        namePlusPasswordToId.put(name + password, userId);
                    } else {
                        throw new AuthenticationException();
                    }
                }
            }
            finally {
                DbUtility.returnConnection(connection);
            }
        }

        if(!expectedPassword.equals(password))
            throw new AuthenticationException();

        int userId = namePlusPasswordToId.get(name + password);
        Teacher teacher = TeacherDao.getTeacherByID(userId);
        if(teacher == null) {
            return StudentDao.getStudentByID(userId);
        } else {
            return teacher;
        }
    }
}
