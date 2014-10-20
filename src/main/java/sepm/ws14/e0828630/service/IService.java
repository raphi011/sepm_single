package sepm.ws14.e0828630.service;

import sepm.ws14.e0828630.domain.DomainObject;

import javax.xml.ws.Service;
import java.util.List;

public interface IService<T extends DomainObject> {



    void create(T entity) throws ServiceException;
    void change(T entity) throws ServiceException;
    void delete(T entity) throws ServiceException;
    List<T> getAll() throws ServiceException;
    List<T> search(String query) throws ServiceException;
}