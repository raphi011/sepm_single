import org.h2.fulltext.FullText;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcHorseDao implements IDao<Horse> {
  //  private static final Logger log = LogManager.getLogger(JdbcJockeyDao.class);

    private Connection con;

    public JdbcHorseDao(Connection con) {
        this.con = con;
    }


    public void create(Horse entity) throws DAOException {
        try {
            if (entity == null)
                throw new IllegalArgumentException();

            PreparedStatement s = con.prepareStatement("INSERT INTO Horse (Name, MaxSpeed, MinSpeed, Image)" +
                    "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            s.setString(1, entity.getName());
            s.setFloat(2, entity.getMinSpeed());
            s.setFloat(3, entity.getMaxSpeed());
            s.setBlob(4, new ByteArrayInputStream(entity.getImage()));

            s.executeUpdate();

            ResultSet rs = s.getGeneratedKeys();
            if (!rs.next())
                throw new SQLException("insert hasn't returned a primary key");

            entity.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public Horse read(int id) throws DAOException {
        try {
            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery("SELECT Name, MaxSpeed, MinSpeed, Image, IsDeleted FROM Horse WHERE IsDeleted = FALSE and HorseId = " + id);

            if (!rs.next())
                return null;

            Blob imageBlob = rs.getBlob("Image");


            Horse horse = new Horse(
            id, rs.getString("Name"),
            rs.getFloat("MaxSpeed"),
            rs.getFloat("MinSpeed"),
            rs.getBoolean("IsDeleted"));


            if (imageBlob != null) {
                horse.setImage(imageBlob.getBytes(0, (int) imageBlob.length()));
            }

            s.close();

            return horse;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void update(Horse entity)  throws DAOException{

        try {
            PreparedStatement s = con.prepareStatement("UPDATE Horse SET Name = ?, MaxSpeed = ?, MinSpeed = ?, Image = ? WHERE HorseId = ?");

            s.setString(1, entity.getName());
            s.setFloat(2, entity.getMaxSpeed());
            s.setFloat(3, entity.getMinSpeed());
            s.setBinaryStream(4, new ByteArrayInputStream(entity.getImage()));
            s.setInt(5, entity.getId());

            s.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void delete(Horse entity)  throws DAOException {
        try {
            Statement s = con.createStatement();

            s.executeUpdate("UPDATE Horse SET IsDeleted = true WHERE HorseId = " + entity.getId());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Horse> search(String query) throws DAOException {
        try {
            ResultSet rs = FullText.searchData(con, query, 0, 0);

            ArrayList<Horse> results = new ArrayList<Horse>();

            while (rs.next()) {
                if (rs.getString("TABLE").equals("HORSE")) {
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

    public List<Horse> readAll() throws DAOException {
        try {
            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery("SELECT HorseId FROM Horse WHERE IsDeleted = false");

            ArrayList<Horse> list = new ArrayList<Horse>();

            while (rs.next()) {
                list.add(read(rs.getInt("HorseId")));
            }

            s.close();

            return list;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
