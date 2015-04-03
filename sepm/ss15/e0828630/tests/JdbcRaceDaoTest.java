import org.junit.After;
import org.junit.Before;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcRaceDaoTest extends AbstractJdbcRaceDaoTest {

    private Connection con;

    @Before
    public void setUp() throws SQLException, DAOException {

        con = H2ConnectionFactory.getConnection();

        JdbcRaceDao raceDao = new JdbcRaceDao(con);

        setRaceDao(raceDao);

        con.setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        con.rollback();
    }
}
