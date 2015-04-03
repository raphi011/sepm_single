import java.util.Collection;

public interface IService {

    void createHorse(Horse h) throws ServiceException;
    void deleteHorse(Horse h) throws ServiceException;
    void updateHorse(Horse h) throws ServiceException;

    void createJockey(Jockey jockey) throws ServiceException;
    void deleteJockey(Jockey jockey) throws ServiceException;
    void updateJockey(Jockey jockey) throws ServiceException;

    void doRaceSimulation(Collection<Race> raceParticipants) throws ServiceException;

    void createRace(Race race) throws ServiceException;
}
