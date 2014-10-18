package sepm.ws14.e0828630.service;

import sepm.ws14.e0828630.domain.Horse;

import java.util.List;

public class HorseService implements IService<Horse> {

    @Override
    public void create(Horse entity) throws ServiceException {

    }

    @Override
    public void change(Horse entity) throws ServiceException {

    }

    @Override
    public void delete(Horse entity) throws ServiceException {

    }

    @Override
    public boolean validate(Horse Entity) throws ServiceException {
        return false;
    }

    @Override
    public List<Horse> getAll() throws ServiceException {
        return null;
    }

    @Override
    public List<Horse> search(String query) throws ServiceException {
        return null;
    }
}
