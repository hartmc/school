package com.lindauer.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * simple connection factory
 *
 * @author clindauer
 * @since 10/20/14
 */
public class DbUtility {

    private static List<Connection> connections = new ArrayList<>();

    public synchronized static Connection getConnection() throws IOException {
        if(connections.size() > 0) {
            return connections.remove(0);
        }
        else {
            try {
                Properties properties = new Properties();
                InputStream inputStream = DbUtility.class.getClassLoader().getResourceAsStream("db.properties");
                properties.load(inputStream);
                String driver = properties.getProperty("driver");
                String url = properties.getProperty("url");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");
                Class.forName(driver);
                Connection connection = DriverManager.getConnection(url, user, password);
                return connection;
            } catch (ClassNotFoundException | SQLException | IOException e) {
                throw new IOException(e);
            }
         }
    }

    public synchronized static void returnConnection(Connection connection) {
        connections.add(connection);
    }
}
