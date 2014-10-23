package sepm.ws14.e0828630.gui;

import sepm.ws14.e0828630.service.Service;

public class AbstractController {

    protected MainApplication mainApplication;
    protected Service service;

    public void setMainApplication(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }
    public void setService(Service service) { this.service = service; }
}
