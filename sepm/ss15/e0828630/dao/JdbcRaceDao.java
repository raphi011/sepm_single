import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcRaceDao implements IDao<Race> {
    //private static final Logger log = LogManager.getLogger(JdbcJockeyDao.class);

    private Connection con;

    public JdbcRaceDao(Connection con) {
        this.con = con;
    }

    public void create(Race entity) throws DAOException {
        try {
            if (entity == null)
                throw new IllegalArgumentException();

            PreparedStatement s = con.prepareStatement("INSERT INTO Race (RaceId, JockeyId, HorseId, Luck, Speed, Rank) " +
                    "VALUES (?,?,?,?,?, ?)");

            s.setInt(1, entity.getId());
            s.setInt(2, entity.getJockeyId());
            s.setInt(3, entity.getHorseId());
            s.setFloat(4, entity.getLuck());
            s.setFloat(5, entity.getSpeed());
            s.setFloat(6, entity.getRank());

            s.executeUpdate();

            //ResultSet rs = s.getGeneratedKeys();
            //if (!rs.next())
            //    throw new SQLException("insert hasn't returned a primary key");

            // entity.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public Race read(int id) throws DAOException {

        try {
            Statement s = con.createStatement();


            ResultSet rs = s.executeQuery("SELECT JockeyId, HorseId, Luck, Speed, Rank FROM Race WHERE RaceId= " + id);

            if (!rs.next())
                return null;

            Race Race = new Race(id, rs.getInt("JockeyId"),
                                     rs.getInt("HorseId"),
                                     rs.getFloat("Luck"),
                                     rs.getFloat("Speed"),
                                     rs.getInt("Rank"));
            s.close();

            return Race;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void update(Race entity)  throws DAOException{
            throw new DAOException("Can't update race entries");
    }

    public void delete(Race entity)  throws DAOException{
        try {
            Statement s = con.createStatement();

            s.executeUpdate("DELETE FROM Race WHERE RaceId = " + entity.getId());
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Race> search(String query)  throws DAOException{
        // todo

        throw new DAOException("Race search not implemented yet");
//        ResultSet rs = FullText.searchData(con, query, 0, 0);
//
//        ArrayList<Race> results = new ArrayList<Race>();
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

    public List<Race> readAll() throws DAOException {
        try {
            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery("SELECT RaceId FROM Race");

            ArrayList<Race> list = new ArrayList<Race>();

            while (rs.next()) {
                list.add(read(rs.getInt("RaceId")));
            }

            s.close();

            return list;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
