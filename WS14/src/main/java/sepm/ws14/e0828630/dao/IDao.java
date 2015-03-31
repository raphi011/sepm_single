package sepm.ws14.e0828630.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {

    void create(T entity) throws DAOException, IllegalArgumentException;
    T read(int id) throws DAOException;
    void update(T entity) throws DAOException;
    void delete(T entity) throws DAOException;
    List<T> search(String query) throws DAOException;
    List<T> readAll() throws DAOException;
}
