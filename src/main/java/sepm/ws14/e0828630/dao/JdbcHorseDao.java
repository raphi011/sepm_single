package sepm.ws14.e0828630.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.fulltext.FullText;
import sepm.ws14.e0828630.domain.Horse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcHorseDao implements IDao<Horse> {
  //  private static final Logger log = LogManager.getLogger(JdbcCustomerDao.class);

    private Connection con;

    public JdbcHorseDao(Connection con) {
        this.con = con;
    }


    @Override
    public void create(Horse entity) throws SQLException {
        if (entity == null)
            throw new IllegalArgumentException();

        PreparedStatement s = con.prepareStatement("INSERT INTO Horse (Name,BirthDate,Weight,Height)" +
                                                   "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        s.setString(1, entity.getName());
        s.setDate(2, new java.sql.Date(entity.getBirthDate().getTime()));
        s.setDouble(3, entity.getWeight());
        s.setInt(4, entity.getHeight());

        s.executeUpdate();

        ResultSet rs = s.getGeneratedKeys();
        if (!rs.next())
            throw new SQLException("insert hasn't returned a primary key");

        entity.setId(rs.getInt(1));
    }

    @Override
    public Horse read(int id) throws SQLException {
        Statement s = con.createStatement();

        ResultSet rs = s.executeQuery("SELECT HorseId, Name, BirthDate, Weight, Height, "
                                    + "Image, Created, IsDeleted FROM Horse WHERE IsDeleted = FALSE and HorseId = " + id);

        if (!rs.next())
            return null;

        Horse horse = new Horse(rs.getBoolean("IsDeleted"),
                                rs.getInt("HorseId"),
                                rs.getString("Name"),
                                rs.getDate("BirthDate"),
                                rs.getDouble("Weight"),
                                rs.getInt("Height"),
                                rs.getDate("Created"));

        s.close();

        return horse;
    }

    @Override
    public void update(Horse entity)  throws SQLException{

        PreparedStatement s = con.prepareStatement("UPDATE Horse SET Name = ?, BirthDate = ?, Weight = ?, Height = ? WHERE HorseId = ?");
        s.setString(1, entity.getName());
        s.setDate(2, new java.sql.Date(entity.getBirthDate().getTime()));
        s.setDouble(3, entity.getWeight());
        s.setInt(4, entity.getHeight());
        s.setInt(5, entity.getId());

        s.executeUpdate();
    }

    @Override
    public void delete(Horse entity)  throws SQLException{
        Statement s = con.createStatement();

        s.executeUpdate("UPDATE Horse SET IsDeleted = true WHERE HorseId = " + entity.getId());
    }

    @Override
    public List<Horse> search(String query)  throws SQLException{
        ResultSet rs = FullText.searchData(con, query, 0, 0);

        ArrayList<Horse> results = new ArrayList<Horse>();

        while (rs.next()) {
            if (rs.getString("TABLE").equals("HORSE")) {
                String stringId = (String)((Object[])rs.getArray("KEYS").getArray())[0];

                int id = Integer.parseInt(stringId);
                results.add(read(id));
            }
        }

        rs.close();
        return results;
    }
}
