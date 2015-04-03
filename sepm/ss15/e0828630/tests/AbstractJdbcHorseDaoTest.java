import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public abstract class AbstractJdbcHorseDaoTest {

    protected HorseDao horseDao;

    protected void setHorseDao(HorseDao horseDao) {
        this.horseDao = horseDao;
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNullShouldThrowException() throws DAOException {
        horseDao.create(null);
    }

    @Test
    public void createNew() throws DAOException {
        Horse h = new Horse("Abel", 60, 90);

        horseDao.create(h);

        assertNotEquals("Primary Key was not set", 0, h.getHorseId());

        Horse h2 = horseDao.read(h.getHorseId());

        assertEquals("Name was not set", h.getName(), h2.getName());
    }

    @Test
    public void delete() throws DAOException {
        Horse h = new Horse("Abel der Zweite", 60, 90);

        horseDao.create(h);
        horseDao.delete(h.getHorseId());
        assertNull("Horse was not deleted!", horseDao.read(h.getHorseId()));
    }

    @Test
    public void update() throws DAOException {
        Horse h = new Horse("Abel der Dritte", 55, 99);

        horseDao.create(h);
        h.setName("Trudel");
        horseDao.update(h);
        h = horseDao.read(h.getHorseId());

        assertEquals("Horse was not updated", "Trudel", h.getName());
    }

    @Test
    public void search() throws DAOException {
        Horse h = new Horse("Abel der Vierte", 51, 60);


        horseDao.create(h);

        assertNotEquals("No search results found", 0, horseDao.search("%abel%").size());
    }
}
