package sepm.ws14.e0828630.dao;

import org.junit.After;
import org.junit.Before;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcCustomerDaoTest extends AbstractJdbcCustomerDaoTest {

    private Connection con;

    @Before
    public void setUp() throws SQLException, DAOException {

        con = H2ConnectionFactory.getConnection();

        JdbcCustomerDao customerDao = new JdbcCustomerDao(con);

        setCustomerDao(customerDao);

        con.setAutoCommit(false);

    }

    @After
    public void tearDown() throws SQLException {
        con.rollback();
    }
}
