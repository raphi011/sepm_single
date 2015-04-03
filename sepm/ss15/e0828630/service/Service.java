import java.util.*;

public class Service implements IService {

    private RaceDao raceDao;
    private JockeyDao jockeyDao;
    private HorseDao horseDao;

    private Collection<Horse> horseList;
    private Collection<Race> raceList;
    private Collection<Jockey> jockeyList;

    public Collection<Horse> getHorseList() {
        return horseList;
    }

    public Collection<Jockey> getJockeyList() {
        return jockeyList;
    }

    public Collection<Race> getRaceList() {
        return raceList;
    }

    public Service(HorseDao horseDao, RaceDao raceDao, JockeyDao jockeyDao) {
        this.horseDao = horseDao;
        this.raceDao = raceDao;
        this.jockeyDao = jockeyDao;
    }

    public void initialize() throws ServiceException {
        Map<Integer, Horse> horses = getAllHorses();
        Map<Integer, Jockey> jockeys = getAllJockeys();
        raceList = getAllRaces();

        for (Race r : raceList) {
            r.setHorse(horses.get(r.getHorse()));
            r.setJockey(jockeys.get(r.getJockeyId()));
        }

        horseList = horses.values();
        jockeyList = jockeys.values();
    }

    private float averageSpeed(Race raceParticipant) {
        float jockeySkill = raceParticipant.getJockey().getSkill();
        float minSpeed = raceParticipant.getHorse().getMinSpeed();
        float maxSpeed = raceParticipant.getHorse().getMaxSpeed();

        Random rnd = new Random();

        double skill = 1 + (0.15 * 1/Math.PI * Math.atan(1/5*jockeySkill));

        double speed = minSpeed + (rnd.nextFloat() % (maxSpeed-minSpeed));

        double luck = 0.95 + (rnd.nextFloat() % 0.10);

        return (float)(speed * skill * luck);
    }

    public void doRaceSimulation(Collection<Race> raceParticipants) throws ServiceException {

        SortedSet<Race> results = new TreeSet<Race>();

        for (Race r : raceParticipants) {
            float averageSpeed = averageSpeed(r);
            r.setSpeed(averageSpeed);
            results.add(r);
        }

        try {
            int rank = 1;

            // todo: make sure iteration order is ascending!
            for (Race r : results) {
                r.setRank(rank++);
                raceDao.create(r);
                raceList.add(r);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void createJockey(Jockey jockey) throws ServiceException {
        if (jockey.getName() == null || jockey.getName().isEmpty()) {
            throw new ServiceException("Name mustn't be empty");
        }

        if (jockey.getJockeyId() != 0)
            throw new ServiceException("Horse with id " + jockey.getJockeyId() + " already exists");

        try {
            jockeyDao.create(jockey);
            jockeyList.add(jockey);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

     public void deleteJockey(Jockey jockey) throws ServiceException {
        if (jockey.getJockeyId() != 0) {
            try {
                jockeyDao.delete(jockey.getJockeyId());
                jockeyList.remove(jockey);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }

        jockeyList.remove(jockey);
    }

    public void updateJockey(Jockey jockey) throws ServiceException {
        if (jockey.getName() == null || jockey.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty");

        try {
            jockeyDao.update(jockey);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private Map<Integer, Jockey> getAllJockeys() throws ServiceException {
        try {
            return jockeyDao.readAll();

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void createRace(Race race) throws ServiceException {
        if (race.getLuck() < 0.95 || race.getLuck() > 1.05) {
            throw new ServiceException("Luck factor has to be between 0.95 and 1.05");
        }

        if (race.getHorseId() == 0) {
            throw new ServiceException("Horse is not set");
        }

        if (race.getJockeyId() == 0) {
            throw new ServiceException("Jockey is not set");
        }

        // todo - auf getraceid == 0 abfragen?

        if (race.getSpeed() < 50 || race.getSpeed() > 100) {
            throw new ServiceException("Speed has to be between 50 and 100");
        }

        try {
            raceDao.create(race);
            raceList.add(race);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private List<Race> getRaceParticipants(int raceId) throws ServiceException {
        try {
            return raceDao.read(raceId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Collection<Race> getAllRaces() throws ServiceException {

        try {
            return raceDao.readAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void createHorse(Horse h) throws ServiceException {
        if (h.getName() == null || h.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty");

        try {
            horseDao.update(h);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteHorse(Horse h) throws ServiceException {

        try {
            horseDao.delete(h.getHorseId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void updateHorse(Horse h) throws ServiceException {
        try {
            horseDao.update(h);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private Map<Integer,Horse> getAllHorses() throws ServiceException {

        try {
            return horseDao.readAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}