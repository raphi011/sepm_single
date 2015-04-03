
import java.sql.Connection;

public class ServiceFactory {

    public static Service createService(PersistanceType type) throws DAOException {

        switch (type)   {
            case H2: return createH2Service();
            default: return null;
        }
    }

    private static Service createH2Service() throws DAOException {
        Connection con = H2ConnectionFactory.getConnection();

        return new Service(new JdbcHorseDao(con), new JdbcRaceDao(con),new JdbcJockeyDao(con));
    }
}
