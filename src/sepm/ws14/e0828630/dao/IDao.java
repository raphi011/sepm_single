package sepm.ws14.e0828630.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

    void create(T entity) throws SQLException;
    T read(int id) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(T entity) throws SQLException;
    List<T> search(String query) throws SQLException;

}
