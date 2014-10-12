package sepm.ws14.e0828630.dao;

import org.h2.fulltext.FullText;
import sepm.ws14.e0828630.domain.Horse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcHorseDao implements IDao<Horse> {

    @Override
    public void create(Horse entity) {

    }

    @Override
    public Horse read(int id) throws SQLException {
        Connection con = H2Connection.getConnection();
        Statement s = con.createStatement();

        ResultSet rs = s.executeQuery("SELECT HorseId, Name, BirthDate, Weight, Height, "
                                    + "Image, Created, IsDeleted FROM Horse WHERE HorseId = " + id);

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
        Connection con = H2Connection.getConnection();
        // todo: update birthdate as well

        PreparedStatement s = con.prepareStatement("UPDATE Horse SET Name = ?, BirthDate = ?, Weight = ?, Height = ?, IsDeleted = ? WHERE HorseId = ?");
        s.setString(1, entity.getName());
        s.setDate(2, new java.sql.Date(entity.getBirthDate().getTime()));
        s.setDouble(3, entity.getWeight());
        s.setInt(4, entity.getHeight());
        s.setBoolean(5, entity.isDeleted());
        s.setInt(6, entity.getHorseId());

        s.executeUpdate();
    }

    @Override
    public void delete(Horse entity)  throws SQLException{
        Connection con = H2Connection.getConnection();
        Statement s = con.createStatement();

        s.executeUpdate("DELETE FROM Horse WHERE HorseId = " + entity.getHorseId());
    }

    @Override
    public List<Horse> search(String query)  throws SQLException{
        Connection con = H2Connection.getConnection();
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
