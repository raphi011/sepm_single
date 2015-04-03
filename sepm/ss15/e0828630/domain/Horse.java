import java.io.ByteArrayInputStream;

public class Horse {

    private String name;
    private int horseId;
    private float maxSpeed;
    private float minSpeed;
    private boolean isDeleted;
    private byte[] image;

    public Horse(int horseId, String name, float minSpeed, float maxSpeed, boolean isDeleted) {
        this.horseId = horseId;
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
        this.isDeleted = isDeleted;
    }

    public Horse(String name, float minSpeed, float maxSpeed) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.minSpeed = minSpeed;
    }

    public int getHorseId() {
        return horseId;
    }

    public ByteArrayInputStream getImageStream() {
        return image != null ?
                new ByteArrayInputStream(image) :
                null;
    }
    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(float minSpeed) {
        this.minSpeed = minSpeed;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
