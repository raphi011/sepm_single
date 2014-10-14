package sepm.ws14.e0828630.domain;

import java.util.Date;

public class Horse extends DomainObject {

    private String name;
    private Date birthDate;
    private double weight;
    private int height;
    private Date created;
    private boolean isDeleted;

    public Horse(String name, Date birthDate, double weight, int height) {
        this.name = name;
        this.birthDate = birthDate;
        this.weight = weight;
        this.height = height;
    }

    public Horse(boolean isDeleted, int id, String name, Date date, double weight, int height, Date created) {
        this.isDeleted = isDeleted;
        this.id = id;
        this.name = name;
        this.birthDate = date;
        this.weight = weight;
        this.height = height;
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date date) {
        this.birthDate = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
