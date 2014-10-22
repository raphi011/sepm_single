package sepm.ws14.e0828630.service;

import sepm.ws14.e0828630.dao.DAOException;
import sepm.ws14.e0828630.dao.IDao;
import sepm.ws14.e0828630.domain.DomainObject;

import java.util.List;

public abstract class AbstractService<T extends DomainObject> implements IService<T> {

    protected IDao<T> dao;

    @Override
    public List<T> search(String query) throws ServiceException {
        try {
            return dao.search(query);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void create(T entity) throws ServiceException {
        try {
            dao.create(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void change(T entity) throws ServiceException {
        try {
            dao.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(T entity) throws ServiceException {
        try {
            dao.delete(entity);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<T> getAll() throws ServiceException {
        try {
            return dao.readAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
