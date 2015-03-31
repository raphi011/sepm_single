
public class Race extends DomainObject {

    private int jockeyId;
    private int horseId;
    private float luck;
    private float speed;
    private int rank;

    public Race(int raceId, int jockeyId, int horseId, float luck, float speed, int rank) {
        super(raceId);
        this.jockeyId = jockeyId;
        this.horseId = horseId;
        this.luck = luck;
        this.speed = speed;
        this.rank = rank;
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
}
