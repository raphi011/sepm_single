package sepm.ws14.e0828630.service;

import sepm.ws14.e0828630.dao.H2ConnectionFactory;
import sepm.ws14.e0828630.dao.JdbcCustomerDao;
import sepm.ws14.e0828630.domain.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerService extends AbstractService<Customer> {


    public CustomerService() throws ServiceException{
        try {
            dao = new JdbcCustomerDao(H2ConnectionFactory.getConnection());
        } catch (SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void create(Customer c) throws ServiceException {
        if (c.getName() == null || c.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty.");

        super.create(c);
    }

    @Override
    public void change(Customer c) throws ServiceException {
        if (c.getName() == null || c.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty.");

        super.change(c);
    }

    @Override
    public void delete(Customer c) throws ServiceException {
        if (c.getId() == 0)
            throw new ServiceException("Customer doesn't exist yet.");

        super.delete(c);
    }

    @Override
    public List<Customer> getAll() throws ServiceException {
        return super.getAll();
    }

    @Override
    public List<Customer> search(String query) throws ServiceException {
        return super.search(query);
    }
}
