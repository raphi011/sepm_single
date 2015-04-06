public abstract class AbstractController {

    protected App mainApplication;
    protected Service service;

    public void setMainApplication(App mainApplication) {
        this.mainApplication = mainApplication;
    }

    public abstract void Query(String query);

    public void setService(Service service) { this.service = service; }
}
