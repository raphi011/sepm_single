package sepm.ws14.e0828630.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {

    private static Connection connection;

    public static Connection openConnection(String connectionString) throws DAOException {

        if (connection != null)
            throw new DAOException("Connection already opened!");

        try {
            Class.forName("org.h2.Driver");
            // return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/databaseName", "sa", "");
            connection = DriverManager.getConnection(connectionString, "sa", "");
            return connection;
        } catch (Exception e) {
            throw new DAOException();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }
}