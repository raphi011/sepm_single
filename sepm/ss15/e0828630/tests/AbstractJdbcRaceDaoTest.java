import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class AbstractJdbcRaceDaoTest {

    protected RaceDao raceDao;

    protected void setRaceDao(RaceDao raceDao) {
        this.raceDao = raceDao;
    }

    @Test
    public void testNewRaceId() throws DAOException {
        int raceId = raceDao.newRaceId();

        assertNotEquals(raceId,0);
    }
}
