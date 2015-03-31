public abstract class DomainObject {

    protected int id;

    public DomainObject() { }

    public DomainObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
