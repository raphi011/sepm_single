package sepm.ws14.e0828630.dao;

import org.junit.Test;
import sepm.ws14.e0828630.domain.Horse;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public abstract class AbstractJdbcHorseDaoTest {

    protected IDao<Horse> horseDao;

    protected void setHorseDao(IDao<Horse> horseDao) {
        this.horseDao = horseDao;
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNullShouldThrowException() throws SQLException{
        horseDao.create(null);
    }

    @Test // test
    public void createNew() throws SQLException {
        Horse h = new Horse("Abel",new Date(), 133, 180);

        horseDao.create(h);

        assertNotEquals("Primary Key was not set", 0, h.getId());

        Horse h2 = horseDao.read(h.getId());

        assertEquals("Name was not set", h.getName(), h2.getName());
    }

    @Test
    public void delete() throws SQLException {
        Horse h = new Horse("Abel",new Date(), 133, 180);

        horseDao.create(h);
        horseDao.delete(h);
        assertNull("Horse was not deleted!", horseDao.read(h.getId()));
    }

    @Test
    public void update() throws SQLException {
        Horse h = new Horse("Abel",new Date(), 133, 180);

        horseDao.create(h);
        h.setName("Trudel");
        horseDao.update(h);
        h = horseDao.read(h.getId());

        assertEquals("Horse was not updated", "Trudel", h.getName());
    }

    @Test
    public void search() throws SQLException {
        Horse h = new Horse("Abel",new Date(), 133, 180);

        horseDao.create(h);

        assertNotEquals("No search results found", 0, horseDao.search("abel").size());
    }

}
