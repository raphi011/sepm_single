package sepm.ws14.e0828630.service;

import org.joda.time.DateTime;
import sepm.ws14.e0828630.dao.*;
import sepm.ws14.e0828630.domain.Horse;

import java.sql.SQLException;
import java.util.List;

public class HorseService extends AbstractService<Horse> {

    public HorseService() throws ServiceException {
        try {
            dao = new JdbcHorseDao(H2ConnectionFactory.getConnection());
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void create(Horse h) throws ServiceException {
        if (h.getName() == null || h.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty");

        if (h.getId() != 0)
            throw new ServiceException("Horse with id " + h.getId() + " already exists");

        super.create(h);
    }

    @Override
    public void change(Horse h) throws ServiceException {
        if (h.getName() == null || h.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty");

        if (h.getBirthDate().isAfter(DateTime.now()))
            throw new ServiceException("Birthdate can't be in the future");

        super.change(h);
    }

    @Override
    public void delete(Horse h) throws ServiceException {
        if (h.getId() == 0)
            throw new ServiceException("Horse doesn't exist yet.");

        super.delete(h);
    }

    @Override
    public List<Horse> getAll() throws ServiceException {
        return super.getAll();
    }

    @Override
    public List<Horse> search(String query) throws ServiceException {

        return super.search(query);
    }
}
