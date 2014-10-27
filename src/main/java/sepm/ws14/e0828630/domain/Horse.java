package sepm.ws14.e0828630.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Horse extends DomainObject {

    private String name;
    private LocalDate birthDate;
    private double weight;
    private int height;
    private LocalDateTime created;
    private boolean isDeleted;
    private byte[] image;

    public Horse(Horse toCopy) {
        this(toCopy.isDeleted(),
                toCopy.id,
                toCopy.name,
                toCopy.birthDate,
                toCopy.weight,
                toCopy.height,
                toCopy.created);
    }

    public Horse() { }

    public Horse(String name, LocalDate birthDate, double weight, int height) {
        this.name = name;
        this.birthDate = birthDate;
        this.weight = weight;
        this.height = height;
    }

    public Horse(boolean isDeleted, int id, String name, LocalDate birthDate, double weight, int height, LocalDateTime created) {
        this.isDeleted = isDeleted;
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate date) {
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }
}
