public interface IService {

    public void createHorse(Horse h) throws ServiceException;
    public void deleteHorse(Horse h) throws ServiceException;
    public void updateHorse(Horse h) throws ServiceException;
    public void getAllHorses() throws ServiceException;

    public void createJockey(Jockey jockey) throws ServiceException;
    public void deleteJockey(Jockey jockey) throws ServiceException;
    public void updateJockey(Jockey jockey) throws ServiceException;
    public void getAllJockeys() throws ServiceException;

    public void createRace(Race race) throws ServiceException;
    public void deleteRace(Race race) throws ServiceException;
    public void updateRace(Race race) throws ServiceException;
    public void getAllRaces() throws ServiceException;
}
