import java.util.List;

public interface HorseDao {
    void create(Horse horse) throws DAOException, IllegalArgumentException;
    Horse read(int id) throws DAOException;
    void update(Horse horse) throws DAOException;
    void delete(int id) throws DAOException;
    List<Horse> search(String name) throws DAOException;
    List<Horse> readAll() throws DAOException;
}
