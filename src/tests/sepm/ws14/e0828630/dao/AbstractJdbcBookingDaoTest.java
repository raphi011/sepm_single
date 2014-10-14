package sepm.ws14.e0828630.dao;

import org.junit.Test;
import sepm.ws14.e0828630.domain.Booking;

import java.sql.SQLException;

public abstract class AbstractJdbcBookingDaoTest {

    protected IDao<Booking> bookingDao;

    protected void setBookingDao(IDao<Booking> bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNullShouldThrowException() throws SQLException{
        bookingDao.create(null);
    }

}
