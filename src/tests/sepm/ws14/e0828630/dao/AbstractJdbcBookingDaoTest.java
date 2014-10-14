package sepm.ws14.e0828630.dao;

import org.junit.Test;
import sepm.ws14.e0828630.domain.Booking;
import sepm.ws14.e0828630.domain.Customer;
import sepm.ws14.e0828630.domain.Horse;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public abstract class AbstractJdbcBookingDaoTest {

    protected IDao<Booking> bookingDao;
    protected IDao<Customer> customerDao;
    protected IDao<Horse> horseDao;

    protected void setHorseDao(IDao<Horse> horseDao) {
        this.horseDao = horseDao;
    }

    protected void setCustomerDao(IDao<Customer> customerDao) {
        this.customerDao = customerDao;
    }

    protected void setBookingDao(IDao<Booking> bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNullShouldThrowException() throws SQLException{
        bookingDao.create(null);
    }

    @Test
    public void createNew() throws SQLException {
        Horse h = new Horse("Abel",new Date(), 133, 180);
        Customer c = new Customer("Raphi");

        horseDao.create(h);
        customerDao.create(c);

        Booking b = new Booking(new Date(), new Date(), h.getId(), c.getId() );

        bookingDao.create(b);

        assertNotEquals("Primary Key was not set", 0, b.getId());
    }

//    @Test
//    public void delete() throws SQLException {
//        Booking c = new Booking("Raphi");
//
//        bookingDao.create(c);
//        bookingDao.delete(c);
//        assertNull("Booking was not deleted!", bookingDao.read(c.getId()));
//    }
//
//    @Test
//    public void update() throws SQLException {
//        Booking c = new Booking("Raphi");
//
//        bookingDao.create(c);
//        c.setName("Raphael");
//        bookingDao.update(c);
//        c = bookingDao.read(c.getId());
//
//        assertEquals("Booking was not updated", "Raphael", c.getName());
//    }
//
//    @Test
//    public void search() throws SQLException {
//        Booking c = new Booking("Raphael Gruber");
//
//        bookingDao.create(c);
//
//        assertNotEquals("No search results found", 0, bookingDao.search("raphael").size());
//    }

}
