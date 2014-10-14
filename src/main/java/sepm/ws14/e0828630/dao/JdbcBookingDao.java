package sepm.ws14.e0828630.dao;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ws14.e0828630.domain.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookingDao implements IDao<Booking> {
    //private static final Logger log = LogManager.getLogger(JdbcCustomerDao.class);

    private Connection con;

    public JdbcBookingDao(Connection con) {
        this.con = con;
    }

    public void create(Booking entity) throws SQLException {
        if (entity == null)
            throw new IllegalArgumentException();

        PreparedStatement s = con.prepareStatement("INSERT INTO Booking (\"From\",\"To\",Horse_HorseId,Customer_CustomerId) " +
                "VALUES (?,?,?,?)");

        s.setDate(1,new java.sql.Date(entity.getFrom().getTime()));
        s.setDate(2, new java.sql.Date(entity.getTo().getTime()));
        s.setDouble(3, entity.getHorseId());
        s.setInt(4, entity.getCustomerId());

        s.executeUpdate();

        ResultSet rs = s.getGeneratedKeys();
        if (!rs.next())
            throw new SQLException("insert hasn't returned a primary key");

        entity.setId(rs.getInt(1));
    }

    @Override
    public Booking read(int id) throws SQLException {
        Statement s = con.createStatement();

        ResultSet rs = s.executeQuery("SELECT \"From\", \"To\", Created, LastChanged, Horse_HorseId, "
                + "Customer_CustomerId FROM Booking WHERE BookingId= " + id);

        if (!rs.next())
            return null;

        Booking booking = new Booking(id,
                rs.getDate("From"),
                rs.getDate("To"),
                rs.getInt("Horse_HorseId"),
                rs.getInt("Customer_CustomerId"),
                rs.getDate("Created"),
                rs.getDate("LastChanged"));

        s.close();

        return booking;
    }

    @Override
    public void update(Booking entity)  throws SQLException{

        PreparedStatement s = con.prepareStatement("UPDATE Booking SET \"From\" = ?, \"To\" = ?, LastChanged = ?, Horse_HorseId = ?, Customer_CustomerId = ? WHERE BookingId = ?");
        s.setDate(1, new java.sql.Date(entity.getFrom().getTime()));
        s.setDate(2, new java.sql.Date(entity.getTo().getTime()));
        s.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
        s.setInt(4, entity.getHorseId());
        s.setInt(5, entity.getCustomerId());
        s.setInt(6, entity.getId());

        s.executeUpdate();
    }

    @Override
    public void delete(Booking entity)  throws SQLException{
        throw new SQLException("Deleting Bookings is not allowed");
//        Statement s = con.createStatement();
//
//        s.executeUpdate("DELETE Booking WHERE BookingId = " + entity.getId());
    }

    @Override
    public List<Booking> search(String query)  throws SQLException{
        // todo
//        ResultSet rs = FullText.searchData(con, query, 0, 0);
//
          ArrayList<Booking> results = new ArrayList<Booking>();
//
//        while (rs.next()) {
//            if (rs.getString("TABLE").equals("")) {
//                String stringId = (String)((Object[])rs.getArray("KEYS").getArray())[0];
//
//                int id = Integer.parseInt(stringId);
//                results.add(read(id));
//            }
//        }
//
//        rs.close();
        return results;
    }
}
