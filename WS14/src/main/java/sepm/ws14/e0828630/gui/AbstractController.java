package sepm.ws14.e0828630.gui;

import sepm.ws14.e0828630.service.Service;

public abstract class AbstractController implements IQuery {

    protected MainApplication mainApplication;
    protected Service service;

    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @Override
    public abstract void Query(String query);

    public void setService(Service service) { this.service = service; }
}
