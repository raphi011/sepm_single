package sepm.ws14.e0828630.dao;

import org.junit.Test;
import sepm.ws14.e0828630.domain.Booking;
import sepm.ws14.e0828630.domain.Horse;

import java.sql.SQLException;

public abstract class AbstractJdbcHorseDaoTest {

    protected IDao<Horse> horseDao;

    protected void setHorseDao(IDao<Horse> horseDao) {
        this.horseDao = horseDao;
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNullShouldThrowException() throws SQLException{
        horseDao.create(null);
    }

}
