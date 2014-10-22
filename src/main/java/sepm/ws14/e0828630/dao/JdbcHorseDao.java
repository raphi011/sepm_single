package sepm.ws14.e0828630.dao;

import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.fulltext.FullText;
import org.joda.time.DateTime;
import sepm.ws14.e0828630.domain.Booking;
import sepm.ws14.e0828630.domain.Horse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcHorseDao implements IDao<Horse> {
  //  private static final Logger log = LogManager.getLogger(JdbcCustomerDao.class);

    private Connection con;

    public JdbcHorseDao(Connection con) {
        this.con = con;
    }


    @Override
    public void create(Horse entity) throws DAOException {
        try {
            if (entity == null)
                throw new IllegalArgumentException();

            PreparedStatement s = con.prepareStatement("INSERT INTO Horse (Name,BirthDate,Weight,Height, Image)" +
                    "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            s.setString(1, entity.getName());
            s.setDate(2, new java.sql.Date(entity.getBirthDate().getMillis()));
            s.setDouble(3, entity.getWeight());
            s.setInt(4, entity.getHeight());
            s.setBlob(5, entity.getImage());

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
    public Horse read(int id) throws DAOException {
        try {
            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery("SELECT HorseId, Name, BirthDate, Weight, Height, "
                    + "Image, Created, IsDeleted, Image FROM Horse WHERE IsDeleted = FALSE and HorseId = " + id);

            if (!rs.next())
                return null;

            Horse horse = new Horse();
            horse.setDeleted(rs.getBoolean("IsDeleted"));
            horse.setId(rs.getInt("HorseId"));
            horse.setName(rs.getString("Name"));
            horse.setBirthDate(new DateTime(rs.getDate("BirthDate").getTime()));
            horse.setWeight(rs.getDouble("Weight"));
            horse.setHeight(rs.getInt("Height"));
            horse.setCreated(new DateTime(rs.getDate("Created").getTime()));

            Blob imageBlob = rs.getBlob("Image");


            if (imageBlob != null) {
                horse.setImage(imageBlob.getBinaryStream());
            }

            s.close();

            return horse;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Horse entity)  throws DAOException{



        try {
            PreparedStatement s = con.prepareStatement("UPDATE Horse SET Name = ?, BirthDate = ?, Weight = ?, Height = ?, Image = ? WHERE HorseId = ?");

            s.setString(1, entity.getName());
            s.setDate(2, new java.sql.Date(entity.getBirthDate().getMillis()));
            s.setDouble(3, entity.getWeight());
            s.setInt(4, entity.getHeight());

            s.setBinaryStream(5, entity.getImage());
            s.setInt(6, entity.getId());

            s.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Horse entity)  throws DAOException {
        try {
            Statement s = con.createStatement();

            s.executeUpdate("UPDATE Horse SET IsDeleted = true WHERE HorseId = " + entity.getId());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
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

    @Override
    public List<Horse> readAll() throws DAOException {
        try {
            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery("SELECT HorseId FROM Horse");

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
