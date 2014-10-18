package sepm.ws14.e0828630.service;

import sepm.ws14.e0828630.dao.H2ConnectionFactory;
import sepm.ws14.e0828630.dao.IDao;
import sepm.ws14.e0828630.dao.JdbcBookingDao;
import sepm.ws14.e0828630.domain.Booking;

import java.sql.SQLException;
import java.util.List;

public class BookingService implements IService<Booking> {

    IDao<Booking> bookingDao;

    public BookingService() throws ServiceException  {
        try {
            bookingDao = new JdbcBookingDao(H2ConnectionFactory.getConnection());
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Booking entity) throws ServiceException {

    }

    @Override
    public void change(Booking entity) throws ServiceException {

    }

    @Override
    public void create(Booking entity) throws ServiceException {
        //if (entity.getFrom() > entity.getTo())
        //    throw new ServiceException("to cant be earlier than from");




    }

    @Override
    public List<Booking> getAll() throws ServiceException {
        return null;
    }

    @Override
    public List<Booking> search(String query) throws ServiceException {
        return null;
    }

    @Override
    public boolean validate(Booking Entity) throws ServiceException {
        return false;
    }
}
