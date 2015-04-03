import java.util.List;

public interface RaceDao {
    void create(Race race) throws DAOException, IllegalArgumentException;
    int newRaceId() throws DAOException;
    List<Race> read(int id) throws DAOException;
    void update(Race race) throws DAOException;
    void delete(int id) throws DAOException;
    List<Race> search(int jockeyId, int raceId, int horseId) throws DAOException;
    List<Race> readAll() throws DAOException;
}
