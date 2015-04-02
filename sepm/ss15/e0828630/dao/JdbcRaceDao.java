import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcRaceDao implements RaceDao {
    //private static final Logger log = LogManager.getLogger(JdbcJockeyDao.class);

    private Connection con;

    private PreparedStatement createStatement;
    private PreparedStatement readStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement readAllStatement;

    public JdbcRaceDao(Connection con) {

        this.con = con;

        try {
            createStatement = con.prepareStatement("INSERT INTO Race (RaceId, JockeyId, HorseId, Luck, Speed, Rank) " +
                    "VALUES (?,?,?,?,?, ?)");

            readStatement = con.prepareStatement("SELECT JockeyId, HorseId, Luck, Speed, Rank FROM Race WHERE RaceId = ?");

            readAllStatement = con.prepareStatement("SELECT RaceId, JockeyId, HorseId, Luck, Speed, Rank FROM Race");

            deleteStatement = con.prepareStatement("DELETE FROM Race WHERE RaceId = ? and JockeyId = ? and HorseId = ?");

        } catch (SQLException e) {
            // log exception ...
        }
    }

    public void create(Race entity) throws DAOException {
        try {
            if (entity == null)
                throw new IllegalArgumentException();

            createStatement.clearParameters();

            createStatement.setInt(1, entity.getRaceId());
            createStatement.setInt(2, entity.getJockeyId());
            createStatement.setInt(3, entity.getHorseId());
            createStatement.setFloat(4, entity.getLuck());
            createStatement.setFloat(5, entity.getSpeed());
            createStatement.setFloat(6, entity.getRank());

            createStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Race> read(int id) throws DAOException {
        try {

            readStatement.clearParameters();

            readStatement.setInt(1, id);

            ResultSet rs = readStatement.executeQuery();

            ArrayList<Race> list = new ArrayList<Race>();

            while (rs.next()) {
                Race race = new Race(id, rs.getInt("JockeyId"),
                                         rs.getInt("HorseId"),
                                         rs.getFloat("Luck"),
                                         rs.getFloat("Speed"),
                                         rs.getInt("Rank"));

                list.add(race);
            }

            rs.close();

            return list;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Race> readAll() throws DAOException {
        try {

            readAllStatement.clearParameters();

            ResultSet rs = readStatement.executeQuery();

            ArrayList<Race> list = new ArrayList<Race>();

            while (rs.next()) {
                Race race = new Race(rs.getInt("RaceId"),
                                     rs.getInt("JockeyId"),
                                     rs.getInt("HorseId"),
                                     rs.getFloat("Luck"),
                                     rs.getFloat("Speed"),
                                     rs.getInt("Rank"));

                list.add(race);
            }

            rs.close();

            return list;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void update(Race entity)  throws DAOException{
            throw new DAOException("Can't update race entries");
    }

    public void delete(int id) throws DAOException {
        try {
            deleteStatement.clearParameters();

            deleteStatement.setInt(1, id);


        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Race> search(int jockeyId, int raceId, int horseId) throws DAOException {
        throw new DAOException("Race search not implemented yet");
    }


}
