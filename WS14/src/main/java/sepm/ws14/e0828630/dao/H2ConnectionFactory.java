package sepm.ws14.e0828630.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionFactory {

    private static H2ConnectionFactory instance = new H2ConnectionFactory();

    public static final String URL = "jdbc:h2:~/test";
    public static final String USER = "sa";
    public static final String PASSWORD = "";
    public static final String DRIVER_CLASS = "org.h2.Driver";

    private H2ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() throws DAOException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public static Connection getConnection() throws DAOException {
        return instance.createConnection();
    }
}
