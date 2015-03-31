package sepm.ws14.e0828630.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import sepm.ws14.e0828630.domain.Customer;

import java.sql.SQLException;

public abstract class AbstractJdbcCustomerDaoTest {

    protected IDao<Customer> customerDao;

    protected void setCustomerDao(IDao<Customer> customerDao) {
        this.customerDao = customerDao;
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNullShouldThrowException() throws DAOException {
        customerDao.create(null);
    }

    @Test
    public void createNew() throws DAOException {
        Customer c = new Customer("Raphi");

        customerDao.create(c);

        assertNotEquals("Primary Key was not set", 0, c.getId());

        Customer c2 = customerDao.read(c.getId());

        assertEquals("Name was not set", c.getName(), c2.getName());
    }

    @Test
    public void delete() throws DAOException {
        Customer c = new Customer("Raphi");

        customerDao.create(c);
        customerDao.delete(c);
        assertNull("Customer was not deleted!", customerDao.read(c.getId()));
    }

    @Test
    public void update() throws DAOException {
        Customer c = new Customer("Raphi");

        customerDao.create(c);
        c.setName("Raphael");
        customerDao.update(c);
        c = customerDao.read(c.getId());

        assertEquals("Customer was not updated", "Raphael", c.getName());
    }

    @Test
    public void search() throws DAOException {
        Customer c = new Customer("Raphael Gruber");

        customerDao.create(c);

        assertNotEquals("No search results found", 0, customerDao.search("raphael").size());
    }
}
