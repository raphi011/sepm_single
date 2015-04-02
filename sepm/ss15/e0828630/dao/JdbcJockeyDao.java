import org.h2.fulltext.FullText;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcJockeyDao {
    //  private static final Logger log = LogManager.getLogger(JdbcJockeyDao.class);

    private Connection con;

    private PreparedStatement createStatement;
    private PreparedStatement readStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement readAllStatement;


    public JdbcJockeyDao(Connection con) {
        this.con = con;

        try {
            createStatement = con.prepareStatement("INSERT INTO Jockey (Name, Skill, BirthDate) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            readStatement = con.prepareStatement("SELECT Name, Skill, BirthDate, IsDeleted FROM Jockey WHERE IsDeleted = FALSE and JockeyId = ?");

            updateStatement = con.prepareStatement("UPDATE Jockey SET Name = ?, Skill = ?, BirthDate = ?, IsDeleted = ? WHERE JockeyId = ?");

            deleteStatement = con.prepareStatement("UPDATE Jockey SET IsDeleted = true WHERE JockeyId = ?");

            readAllStatement =  con.prepareStatement("SELECT JockeyId FROM Jockey WHERE IsDeleted = false");

        } catch (SQLException e) {
            // log exception ...
        }
    }

    public void create(Jockey entity) throws DAOException {
        try {
            if (entity == null)
                throw new IllegalArgumentException();

            createStatement.clearParameters();

            createStatement.setString(1, entity.getName());
            createStatement.setFloat(2, entity.getSkill());
            createStatement.setDate(3, new java.sql.Date(entity.getBirthDate().getTime()));
            createStatement.executeUpdate();

            ResultSet rs = createStatement.getGeneratedKeys();
            if (!rs.next())
                throw new SQLException("insert hasn't returned a primary key");

            entity.setJockeyId(rs.getInt(1));
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public Jockey read(int id) throws DAOException {
        try {
            readStatement.clearParameters();

            readStatement.setInt(1,id);

            ResultSet rs = readStatement.executeQuery();

            if (!rs.next())
                return null;

            Jockey jockey = new Jockey(id,
                    rs.getString("Name"),
                    rs.getFloat("Skill"),
                    rs.getDate("Date"),
                    rs.getBoolean("IsDeleted"));


            rs.close();

            return jockey;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void update(Jockey entity) throws DAOException {
        try {
            updateStatement.clearParameters();

            updateStatement.setString(1, entity.getName());
            updateStatement.setFloat(2, entity.getSkill());
            updateStatement.setDate(3, new java.sql.Date(entity.getBirthDate().getTime()));
            updateStatement.setBoolean(4, entity.isDeleted());
            updateStatement.setInt(5, entity.getJockeyId());

            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void delete(Jockey entity) throws DAOException {
        try {
            deleteStatement.clearParameters();

            deleteStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Jockey> search(String query) throws DAOException {
        try {
            ResultSet rs = FullText.searchData(con, query, 0, 0);

            ArrayList<Jockey> results = new ArrayList<Jockey>();

            while (rs.next()) {
                if (rs.getString("TABLE").equals("Jockey")) {
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

    public List<Jockey> readAll() throws DAOException {
        try {

            ResultSet rs = readAllStatement.executeQuery();

            ArrayList<Jockey> list = new ArrayList<Jockey>();

            while (rs.next()) {
                list.add(read(rs.getInt("JockeyId")));
            }

            rs.close();

            return list;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
