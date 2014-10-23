package sepm.ws14.e0828630.service;

import sepm.ws14.e0828630.domain.Booking;
import sepm.ws14.e0828630.domain.Customer;
import sepm.ws14.e0828630.domain.Horse;

import java.util.List;

public interface IService {

    public void createHorse(Horse h) throws ServiceException;
    public void deleteHorse(Horse h) throws ServiceException;
    public void updateHorse(Horse h) throws ServiceException;
    public void getAllHorses() throws ServiceException;

    public void createCustomer(Customer customer) throws ServiceException;
    public void deleteCustomer(Customer customer) throws ServiceException;
    public void updateCustomer(Customer customer) throws ServiceException;
    public void getAllCustomers() throws ServiceException;

    public void createBooking(Booking booking) throws ServiceException;
    public void deleteBooking(Booking booking) throws ServiceException;
    public void updateBooking(Booking booking) throws ServiceException;
    public void getAllBookings() throws ServiceException;
}
