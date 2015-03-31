package sepm.ws14.e0828630.service;

import sepm.ws14.e0828630.dao.*;

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

        return new Service(new JdbcHorseDao(con), new JdbcBookingDao(con),new JdbcCustomerDao(con));
    }
}
