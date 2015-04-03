import java.util.List;

public class Service implements IService {

    private RaceDao raceDao;
    private JockeyDao jockeyDao;
    private HorseDao horseDao;

    private List<Horse> horseList;
    private List<Race> raceList;
    private List<Jockey> jockeyList;

    public List<Horse> getHorseList() {
        return horseList;
    }

    public List<Jockey> getJockeyList() {
        return jockeyList;
    }

    public List<Race> getRaceList() {
        return raceList;
    }

    public Service(HorseDao horseDao, RaceDao raceDao, JockeyDao jockeyDao) {
        this.horseDao = horseDao;
        this.raceDao = raceDao;
        this.jockeyDao = jockeyDao;
    }

    public void createJockey(Jockey jockey) throws ServiceException {
        if (jockey.getName() == null || jockey.getName().isEmpty()) {
            throw new ServiceException("Name mustn't be empty");
        }

        if (jockey.getJockeyId() != 0)
            throw new ServiceException("Horse with id " + jockey.getJockeyId() + " already exists");

        try {
            jockeyDao.create(jockey);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteJockey(Jockey jockey) throws ServiceException {
        if (jockey.getJockeyId() != 0) {
            try {
                jockeyDao.delete(jockey.getJockeyId());
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

    public void getAllJockeys() throws ServiceException {
        try {
            jockeyList = jockeyDao.readAll();
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
/*

    public void deleteRaceParticipant(Race race) throws ServiceException {



        try {
            bookingDao.delete(b);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void updateRace(Race race) throws ServiceException {

    }

    public void getAllRaces() throws ServiceException {

    }

    public void createBooking(Booking b) throws ServiceException {
        //    if (b.getFrom().isAfter(b.getTo()))
        //        throw new ServiceException("'From' has to before 'to");

        if (b.getCustomerId() == 0)
            throw new ServiceException("Customer is not set");

        if (b.getHorseId() == 0)
            throw new ServiceException("Horse is not set");

        try {
            bookingDao.create(b);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteBooking(Booking b) throws ServiceException {
        //  todo if (!b.isEditable())
        //     throw new ServiceException("A booking can't be deleted within the last two weeks.");

        try {
            bookingDao.delete(b);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void updateBooking(Booking b) throws ServiceException {
        // if (!b.isEditable())
        // todo     throw new ServiceException("Can't change booking details within the last two weeks.");

        //   if (b.getFrom().isAfter(b.getTo()))
        //     throw new ServiceException("'From' has to before 'to");

        try {
            bookingDao.update(b);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void getAllBookings() throws ServiceException {
        try {
            bookingDao.readAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void createHorse(Horse h) throws ServiceException {
        if (h.getName() == null || h.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty");

        if (h.getHorseId() != 0)
            throw new ServiceException("Horse with id " + h.getHorseId() + " already exists");

        try {
            horseDao.create(h);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteHorse(Horse h) throws ServiceException {
        if (h.getHorseId() == 0)
            throw new ServiceException("Horse doesn't exist yet.");

        try {
            horseDao.delete(h.getHorseId());
            horseList.remove(h);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void updateHorse(Horse h) throws ServiceException {
        if (h.getName() == null || h.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty");

        // todo if (h.getBirthDate().isAfter(DateTime.now()))
        // throw new ServiceException("Birthdate can't be in the future");

        try {
            horseDao.update(h);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void getAllHorses() throws ServiceException {
        try {
            horseList = horseDao.readAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void createCustomer(Customer c) throws ServiceException {
        if (c.getName() == null || c.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty.");
    }

    public void deleteCustomer(Customer c) throws ServiceException {
        if (c.getId() == 0)
            throw new ServiceException("Customer doesn't exist yet.");

        try {
            customerDao.delete(c);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void updateCustomer(Customer c) throws ServiceException {
        if (c.getName() == null || c.getName().isEmpty())
            throw new ServiceException("Name mustn't be empty.");

        try {
            customerDao.update(c);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void getAllCustomers() throws ServiceException {
        try {
            customerDao.readAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }*/


}