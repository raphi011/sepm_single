package sepm.ws14.e0828630.dao;

import org.h2.fulltext.FullText;
import sepm.ws14.e0828630.domain.Customer;
import sepm.ws14.e0828630.domain.Horse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCustomerDao implements IDao<Customer> {

    @Override
    public void create(Customer entity) throws SQLException {
        Connection con = H2Connection.getConnection();
        PreparedStatement s = con.prepareStatement("INSERT INTO Customer (Name) VALUES (?)");

        s.setString(1, entity.getName());

        s.executeUpdate();
    }

    @Override
    public Customer read(int id) throws SQLException {
        Connection con = H2Connection.getConnection();
        Statement s = con.createStatement();

        ResultSet rs = s.executeQuery("SELECT Name, IsDeleted FROM Customer WHERE CustomerId = " + id);

        if (!rs.next())
            return null;

        Customer customer = new Customer(id,
                rs.getString("Name"),
                rs.getBoolean("IsDeleted"));

        s.close();

        return customer;
    }

    @Override
    public void update(Customer entity)  throws SQLException{
        Connection con = H2Connection.getConnection();

        PreparedStatement s = con.prepareStatement("UPDATE Customer SET Name = ?, IsDeleted = ? WHERE CustomerId = ?");
        s.setString(1, entity.getName());
        s.setBoolean(2, entity.isDeleted());
        s.setInt(3, entity.getId());

        s.executeUpdate();
    }

    @Override
    public void delete(Customer entity)  throws SQLException{
        Connection con = H2Connection.getConnection();
        Statement s = con.createStatement();

        s.executeUpdate("UPDATE Customer SET IsDeleted = true WHERE CustomerId = " + entity.getId());
    }

    @Override
    public List<Customer> search(String query)  throws SQLException{
        Connection con = H2Connection.getConnection();
        ResultSet rs = FullText.searchData(con, query, 0, 0);

        ArrayList<Customer> results = new ArrayList<Customer>();

        while (rs.next()) {
            if (rs.getString("TABLE").equals("CUSTOMER")) {
                String stringId = (String)((Object[])rs.getArray("KEYS").getArray())[0];

                int id = Integer.parseInt(stringId);
                results.add(read(id));
            }
        }

        rs.close();
        return results;
    }
}
