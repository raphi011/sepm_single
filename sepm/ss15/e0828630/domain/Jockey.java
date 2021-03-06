import java.util.Date;

public class Jockey {


    private int jockeyId;
    private String name;
    private float skill;
    private Date birthDate;
    private boolean isDeleted;

    public Jockey(int jockeyId, String name, float skill, Date birthDate, boolean isDeleted) {
        this.jockeyId = jockeyId;
        this.name = name;
        this.skill = skill;
        this.birthDate = birthDate;
        this.isDeleted = isDeleted;
    }

    public int getJockeyId() {
        return jockeyId;
    }

    public void setJockeyId(int jockeyId) {
        this.jockeyId = jockeyId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSkill() {
        return skill;
    }

    public void setSkill(float skill) {
        this.skill = skill;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
