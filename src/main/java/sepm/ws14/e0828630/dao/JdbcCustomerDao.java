package sepm.ws14.e0828630.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.fulltext.FullText;
import sepm.ws14.e0828630.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCustomerDao implements IDao<Customer> {
    //  private static final Logger log = LogManager.getLogger(JdbcCustomerDao.class);

    private Connection con;

    public JdbcCustomerDao(Connection con) {
        this.con = con;
    }

    @Override
    public void create(Customer entity) throws DAOException {
        try {
            if (entity == null)
                throw new IllegalArgumentException();

            PreparedStatement s = con.prepareStatement("INSERT INTO Customer (Name) VALUES (?)");

            s.setString(1, entity.getName());

            s.executeUpdate();

            ResultSet rs = s.getGeneratedKeys();
            if (!rs.next())
                throw new SQLException("insert hasn't returned a primary key");

            entity.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Customer read(int id) throws DAOException {
        try {
            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery("SELECT Name, IsDeleted FROM Customer WHERE IsDeleted = FALSE and CustomerId = " + id);

            if (!rs.next())
                return null;

            Customer customer = new Customer(id,
                    rs.getString("Name"),
                    rs.getBoolean("IsDeleted"));

            s.close();

            return customer;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Customer entity) throws DAOException {
        try {
            PreparedStatement s = con.prepareStatement("UPDATE Customer SET Name = ?, IsDeleted = ? WHERE CustomerId = ?");
            s.setString(1, entity.getName());
            s.setBoolean(2, entity.isDeleted());
            s.setInt(3, entity.getId());

            s.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Customer entity) throws DAOException {
        try {
            Statement s = con.createStatement();

            s.executeUpdate("UPDATE Customer SET IsDeleted = true WHERE CustomerId = " + entity.getId());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Customer> search(String query) throws DAOException {
        try {
            ResultSet rs = FullText.searchData(con, query, 0, 0);

            ArrayList<Customer> results = new ArrayList<Customer>();

            while (rs.next()) {
                if (rs.getString("TABLE").equals("CUSTOMER")) {
                    String stringId = (String) ((Object[]) rs.getArray("KEYS").getArray())[0];

                    int id = Integer.parseInt(stringId);
                    results.add(read(id));
                }
            }

            rs.close();
            return results;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Customer> readAll() throws DAOException {
        try {
            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery("SELECT CustomerId FROM Customer");

            ArrayList<Customer> list = new ArrayList<Customer>();

            while (rs.next()) {
                list.add(read(rs.getInt("CustomerId")));
            }

            s.close();

            return list;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
