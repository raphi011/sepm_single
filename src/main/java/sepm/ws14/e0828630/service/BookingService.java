package sepm.ws14.e0828630.service;

import sepm.ws14.e0828630.dao.DAOException;
import sepm.ws14.e0828630.dao.H2ConnectionFactory;
import sepm.ws14.e0828630.dao.IDao;
import sepm.ws14.e0828630.dao.JdbcBookingDao;
import sepm.ws14.e0828630.domain.Booking;

import javax.xml.ws.Service;
import java.sql.SQLException;
import java.util.List;

public class BookingService extends AbstractService<Booking> {

    public BookingService() throws ServiceException {
        try {
            dao = new JdbcBookingDao(H2ConnectionFactory.getConnection());
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Booking b) throws ServiceException {
        if (!b.isEditable())
            throw new ServiceException("A booking can't be deleted within the last two weeks.");

        super.delete(b);
    }

    @Override
    public void change(Booking b) throws ServiceException {
        if (!b.isEditable())
            throw new ServiceException("Can't change booking details within the last two weeks.");

        if (b.getFrom().isAfter(b.getTo()))
            throw new ServiceException("'From' has to before 'to");

        super.change(b);
    }

    @Override
    public void create(Booking b) throws ServiceException {
        if (b.getFrom().isAfter(b.getTo()))
            throw new ServiceException("'From' has to before 'to");

        if (b.getCustomerId() == 0)
            throw new ServiceException("Customer is not set");

        if (b.getHorseId() == 0)
            throw new ServiceException("Horse is not set");

        super.create(b);
    }

    @Override
    public List<Booking> getAll() throws ServiceException {

        return super.getAll();
    }

    @Override
    public List<Booking> search(String query) throws ServiceException {

        return super.search(query);
    }
}
