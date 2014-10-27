package sepm.ws14.e0828630.service;

import sepm.ws14.e0828630.dao.DAOException;
import sepm.ws14.e0828630.dao.IDao;
import sepm.ws14.e0828630.domain.Booking;
import sepm.ws14.e0828630.domain.Customer;
import sepm.ws14.e0828630.domain.Horse;

import java.util.List;

public class Service implements IService {

    private IDao<Booking> bookingDao;
    private IDao<Customer> customerDao;
    private IDao<Horse> horseDao;

    private List<Horse> horseList;
    private List<Booking> bookingList;
    private List<Customer> customerList;

    public List<Horse> getHorseList() {
        return horseList;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public Service(IDao<Horse> horseDao, IDao<Booking> bookingDao, IDao<Customer> customerDao) {
        this.horseDao = horseDao;
        this.bookingDao = bookingDao;
        this.customerDao = customerDao;
    }

    @Override
    public void createHorse(Horse h) throws ServiceException {
        if (h.getName() == null || h.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty");

        if (h.getId() != 0)
            throw new ServiceException("Horse with id " + h.getId() + " already exists");

        try {
            horseDao.create(h);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteHorse(Horse h) throws ServiceException {
        if (h.getId() == 0)
            throw new ServiceException("Horse doesn't exist yet.");

        try {
            horseDao.delete(h);
            horseList.remove(h);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateHorse(Horse h) throws ServiceException {
        if (h.getName() == null || h.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty");

        // todo if (h.getBirthDate().isAfter(DateTime.now()))
        // throw new ServiceException("Birthdate can't be in the future");

        try {
            horseDao.update(h);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void getAllHorses() throws ServiceException {
        try {
            horseList = horseDao.readAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createCustomer(Customer c) throws ServiceException {
        if (c.getName() == null || c.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty.");
    }

    @Override
    public void deleteCustomer(Customer c) throws ServiceException {
        if (c.getId() == 0)
            throw new ServiceException("Customer doesn't exist yet.");

        try {
            customerDao.delete(c);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateCustomer(Customer c) throws ServiceException {
        if (c.getName() == null || c.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty.");

        try {
            customerDao.update(c);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void getAllCustomers() throws ServiceException {
        try {
            customerDao.readAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createBooking(Booking b) throws ServiceException {
    //    if (b.getFrom().isAfter(b.getTo()))
    //        throw new ServiceException("'From' has to before 'to");

        if (b.getCustomerId() == 0)
            throw new ServiceException("Customer is not set");

        if (b.getHorseId() == 0)
            throw new ServiceException("Horse is not set");

        try {
            bookingDao.create(b);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteBooking(Booking b) throws ServiceException {
     //  todo if (!b.isEditable())
       //     throw new ServiceException("A booking can't be deleted within the last two weeks.");

        try {
            bookingDao.delete(b);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateBooking(Booking b) throws ServiceException {
       // if (!b.isEditable())
       // todo     throw new ServiceException("Can't change booking details within the last two weeks.");

     //   if (b.getFrom().isAfter(b.getTo()))
       //     throw new ServiceException("'From' has to before 'to");

        try {
            bookingDao.update(b);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void getAllBookings() throws ServiceException {
        try {
            bookingDao.readAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
