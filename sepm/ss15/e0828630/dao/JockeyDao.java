import java.util.List;

public interface JockeyDao {
    void create(Jockey jockey) throws DAOException, IllegalArgumentException;
    Jockey read(int id) throws DAOException;
    void update(Jockey jockey) throws DAOException;
    void delete(int id) throws DAOException;
    List<Jockey> search(String name) throws DAOException;
    List<Jockey> readAll() throws DAOException;
}
