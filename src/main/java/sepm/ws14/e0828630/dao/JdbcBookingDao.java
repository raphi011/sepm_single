package sepm.ws14.e0828630.dao;

import org.joda.time.DateTime;
import sepm.ws14.e0828630.domain.Booking;
import sepm.ws14.e0828630.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBookingDao implements IDao<Booking> {
    //private static final Logger log = LogManager.getLogger(JdbcCustomerDao.class);

    private Connection con;

    public JdbcBookingDao(Connection con) {
        this.con = con;
    }

    public void create(Booking entity) throws DAOException {
        try {
            if (entity == null)
                throw new IllegalArgumentException();

            PreparedStatement s = con.prepareStatement("INSERT INTO Booking (\"From\",\"To\",Horse_HorseId,Customer_CustomerId) " +
                    "VALUES (?,?,?,?)");

            s.setDate(1, new java.sql.Date(entity.getFrom().getMillis()));
            s.setDate(2, new java.sql.Date(entity.getTo().getMillis()));
            s.setDouble(3, entity.getHorseId());
            s.setInt(4, entity.getCustomerId());

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
    public Booking read(int id) throws DAOException {

        try {
            Statement s = con.createStatement();


            ResultSet rs = s.executeQuery("SELECT \"From\", \"To\", Created, LastChanged, Horse_HorseId, "
                    + "Customer_CustomerId, IsCanceled FROM Booking WHERE BookingId= " + id);

            if (!rs.next())
                return null;

            Booking booking = new Booking(id,
                    new DateTime(rs.getDate("From").getTime()),
                    new DateTime(rs.getDate("To").getTime()),
                    rs.getInt("Horse_HorseId"),
                    rs.getInt("Customer_CustomerId"),
                    new DateTime(rs.getDate("Created")),
                    new DateTime(rs.getDate("LastChanged")),
                    rs.getBoolean("IsCanceled"));

            s.close();

            return booking;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Booking entity)  throws DAOException{

        try {
            PreparedStatement s = con.prepareStatement("UPDATE Booking SET \"From\" = ?, \"To\" = ?, LastChanged = ?, Horse_HorseId = ?, Customer_CustomerId = ?, IsCanceled = ? WHERE BookingId = ?");
            s.setDate(1, new java.sql.Date(entity.getFrom().getMillis()));
            s.setDate(2, new java.sql.Date(entity.getTo().getMillis()));
            s.setDate(3, new java.sql.Date(DateTime.now().getMillis()));
            s.setInt(4, entity.getHorseId());
            s.setInt(5, entity.getCustomerId());
            s.setBoolean(6, entity.isCanceled());
            s.setInt(7, entity.getId());

            s.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Booking entity)  throws DAOException{
        try {
            Statement s = con.createStatement();

            s.executeUpdate("DELETE FROM Booking WHERE BookingId = " + entity.getId());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Booking> search(String query)  throws DAOException{
        // todo

        throw new DAOException("Booking search not implemented yet");
//        ResultSet rs = FullText.searchData(con, query, 0, 0);
//
//        ArrayList<Booking> results = new ArrayList<Booking>();
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
        //return results;
    }

    @Override
    public List<Booking> readAll() throws DAOException {
        try {
            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery("SELECT BookingId FROM Booking");

            ArrayList<Booking> list = new ArrayList<Booking>();

            while (rs.next()) {
                list.add(read(rs.getInt("BookingId")));
            }

            s.close();

            return list;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
