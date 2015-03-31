import org.h2.fulltext.FullText;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcJockeyDao implements IDao<Jockey> {
    //  private static final Logger log = LogManager.getLogger(JdbcJockeyDao.class);

    private Connection con;

    public JdbcJockeyDao(Connection con) {
        this.con = con;
    }

    public void create(Jockey entity) throws DAOException {
        try {
            if (entity == null)
                throw new IllegalArgumentException();

            PreparedStatement s = con.prepareStatement("INSERT INTO Jockey (Name, Skill, BirthDate) VALUES (?, ?, ?)");

            s.setString(1, entity.getName());
            s.setFloat(2, entity.getSkill());
            s.setDate(3, new java.sql.Date(entity.getBirthDate().getTime()));
            s.executeUpdate();

            ResultSet rs = s.getGeneratedKeys();
            if (!rs.next())
                throw new SQLException("insert hasn't returned a primary key");

            entity.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public Jockey read(int id) throws DAOException {
        try {
            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery("SELECT Name, Skill, BirthDate, IsDeleted FROM Jockey WHERE IsDeleted = FALSE and JockeyId = " + id);

            if (!rs.next())
                return null;

            Jockey jockey = new Jockey(id,
                    rs.getString("Name"),
                    rs.getFloat("Skill"),
                    rs.getDate("Date"),
                    rs.getBoolean("IsDeleted"));

            s.close();

            return jockey;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void update(Jockey entity) throws DAOException {
        try {
            PreparedStatement s = con.prepareStatement("UPDATE Jockey SET Name = ?, Skill = ?, BirthDate = ?, IsDeleted = ? WHERE JockeyId = ?");
            s.setString(1, entity.getName());
            s.setFloat(2, entity.getSkill());
            s.setDate(3, new java.sql.Date(entity.getBirthDate().getTime()));
            s.setBoolean(4, entity.isDeleted());
            s.setInt(5, entity.getId());

            s.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void delete(Jockey entity) throws DAOException {
        try {
            Statement s = con.createStatement();

            s.executeUpdate("UPDATE Jockey SET IsDeleted = true WHERE JockeyId = " + entity.getId());
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
            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery("SELECT JockeyId FROM Jockey");

            ArrayList<Jockey> list = new ArrayList<Jockey>();

            while (rs.next()) {
                list.add(read(rs.getInt("JockeyId")));
            }

            s.close();

            return list;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
