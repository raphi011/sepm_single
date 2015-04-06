
public class Race implements Comparable<Race> {


    private int raceId;
    private int jockeyId;
    private Jockey jockey;
    private int horseId;
    private Horse horse;
    private float luck;
    private float speed;
    private int rank;


    public Race(int raceId, int jockeyId, int horseId, float luck, float speed, int rank) {
        this.raceId = raceId;
        this.jockeyId = jockeyId;
        this.horseId = horseId;
        this.luck = luck;
        this.speed = speed;
        this.rank = rank;
    }

    public Jockey getJockey() {
        return jockey;
    }

    public void setJockey(Jockey jockey) {
        this.jockey = jockey;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getJockeyId() {
        return jockeyId;
    }

    public void setJockeyId(int jockeyId) {
        this.jockeyId = jockeyId;
    }

    public int getHorseId() {
        return horseId;
    }

    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }

    public float getLuck() {
        return luck;
    }

    public void setLuck(float luck) {
        this.luck = luck;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int compareTo(Race o) {
        if (this.getSpeed() < o.getSpeed()) {
            return -1;
        } else if (this.getSpeed() > o.getSpeed()) {
            return 1;
        } else {
            return 0;
        }
    }
}
