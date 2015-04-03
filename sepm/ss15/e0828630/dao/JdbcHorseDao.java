import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcHorseDao implements HorseDao {
    //private static final Logger log = LogManager.getLogger(JdbcHorseDao.class);

    private Connection con;
    private PreparedStatement createStatement;
    private PreparedStatement readStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement readAllStatement;
    private PreparedStatement searchStatement;

    public JdbcHorseDao(Connection con) {
        this.con = con;

        try {
           createStatement = con.prepareStatement("INSERT INTO Horse (Name, MinSpeed, MaxSpeed, Image)" +
                    "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            readStatement = con.prepareStatement("SELECT Name, MinSpeed, MaxSpeed, Image, IsDeleted FROM Horse WHERE IsDeleted = FALSE and HorseId = ?");

            updateStatement = con.prepareStatement("UPDATE Horse SET Name = ?, MinSpeed = ?, MaxSpeed = ?, Image = ? WHERE HorseId = ?");

            deleteStatement = con.prepareStatement("UPDATE Horse SET IsDeleted = true WHERE HorseId = ?");

            readAllStatement =  con.prepareStatement("SELECT HorseId FROM Horse WHERE IsDeleted = false");

            searchStatement = con.prepareStatement("SELECT HorseId FROM Horse WHERE lower(name) LIKE ? ");


        } catch (SQLException e) {
            // log exception ...
        }
    }

    public void create(Horse entity) throws DAOException {
        try {
            if (entity == null)
                throw new IllegalArgumentException();

            createStatement.clearParameters();

            createStatement.setString(1, entity.getName());
            createStatement.setFloat(2, entity.getMinSpeed());
            createStatement.setFloat(3, entity.getMaxSpeed());
            createStatement.setBlob(4, entity.getImageStream());

            createStatement.executeUpdate();

            ResultSet rs = createStatement.getGeneratedKeys();
            if (!rs.next())
                throw new SQLException("insert hasn't returned a primary key");

            entity.setHorseId(rs.getInt(1));

            rs.close();

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public Horse read(int id) throws DAOException {
        try {
            readStatement.clearParameters();

            readStatement.setInt(1, id);

            ResultSet rs = readStatement.executeQuery();

            if (!rs.next())
                return null;

            Blob imageBlob = rs.getBlob("Image");


            Horse horse = new Horse(
            id, rs.getString("Name"), rs.getFloat("MinSpeed"),
            rs.getFloat("MaxSpeed"),
            rs.getBoolean("IsDeleted"));


            if (imageBlob != null) {
                horse.setImage(imageBlob.getBytes(0, (int) imageBlob.length()));
            }

            rs.close();

            return horse;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void update(Horse entity)  throws DAOException{

        try {
            updateStatement.clearParameters();

            updateStatement.setString(1, entity.getName());
            updateStatement.setFloat(2, entity.getMinSpeed());
            updateStatement.setFloat(3, entity.getMaxSpeed());
            updateStatement.setBinaryStream(4, entity.getImageStream());
            updateStatement.setInt(5, entity.getHorseId());

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void delete(int id) throws DAOException {
        try {
            deleteStatement.clearParameters();

            deleteStatement.setInt(1, id);

            deleteStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Horse> search(String name) throws DAOException {
        try {
            searchStatement.clearParameters();

            searchStatement.setString(1, name);

            ResultSet rs = searchStatement.executeQuery();

            ArrayList<Horse> list = new ArrayList<Horse>();

            while (rs.next()) {
                list.add(read(rs.getInt("HorseId")));
            }

            return list;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

//    public List<Horse> search(String query) throws DAOException {
//        try {
//            ResultSet rs = FullText.searchData(con, query, 0, 0);
//
//            ArrayList<Horse> results = new ArrayList<Horse>();
//
//            while (rs.next()) {
//                if (rs.getString("TABLE").equals("HORSE")) {
//                    String stringId = (String) ((Object[]) rs.getArray("KEYS").getArray())[0];
//
//                    int id = Integer.parseInt(stringId);
//                    results.add(read(id));
//                }
//            }
//
//            rs.close();
//            return results;
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        }
//    }

    public List<Horse> readAll() throws DAOException {
        try {
            ResultSet rs = readAllStatement.executeQuery();

            ArrayList<Horse> list = new ArrayList<Horse>();

            while (rs.next()) {
                list.add(read(rs.getInt("HorseId")));
            }

            rs.close();

            return list;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}