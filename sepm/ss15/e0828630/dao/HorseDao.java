import java.util.List;
import java.util.Map;

public interface HorseDao {
    void create(Horse horse) throws DAOException, IllegalArgumentException;
    Horse read(int id) throws DAOException;
    void update(Horse horse) throws DAOException;
    void delete(int id) throws DAOException;
    List<Horse> search(String name) throws DAOException;
    Map<Integer, Horse> readAll() throws DAOException;
}
