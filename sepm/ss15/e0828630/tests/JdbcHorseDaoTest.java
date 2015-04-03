import org.junit.After;
import org.junit.Before;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcHorseDaoTest extends AbstractJdbcHorseDaoTest {

    private Connection con;

    @Before
    public void setUp() throws SQLException, DAOException {

        con = H2ConnectionFactory.getConnection();

        JdbcHorseDao horseDao = new JdbcHorseDao(con);

        setHorseDao(horseDao);

        con.setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        con.rollback();
    }
}
