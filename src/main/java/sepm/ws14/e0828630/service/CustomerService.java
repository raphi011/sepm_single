package sepm.ws14.e0828630.service;

import sepm.ws14.e0828630.domain.Customer;

import java.util.List;

public class CustomerService implements IService<Customer> {
    @Override
    public void create(Customer entity) throws ServiceException {

    }

    @Override
    public void change(Customer entity) throws ServiceException {

    }

    @Override
    public void delete(Customer entity) throws ServiceException {

    }

    @Override
    public boolean validate(Customer Entity) throws ServiceException {
        return false;
    }

    @Override
    public List<Customer> getAll() throws ServiceException {
        return null;
    }

    @Override
    public List<Customer> search(String query) throws ServiceException {
        return null;
    }
}
