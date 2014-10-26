package sepm.ws14.e0828630.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sepm.ws14.e0828630.dao.DAOException;
import sepm.ws14.e0828630.service.PersistanceType;
import sepm.ws14.e0828630.service.Service;
import sepm.ws14.e0828630.service.ServiceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainApplication extends Application implements IQuery {

    private Stage primaryStage;
    private BorderPane mainLayout;
    private Service service;

    private Map<Tab, AbstractController> tabControllers;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        tabControllers = new HashMap<Tab, AbstractController>();

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Wendy's riding stable");


        try {
            service = ServiceFactory.createService(PersistanceType.H2);
            service.getAllBookings();
            service.getAllCustomers();
            service.getAllHorses();
        } catch (DAOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "An unexpected error occured while initiating the program, exiting.");
            alert.showAndWait();
            System.exit(1);
        }



        initRootLayout();

        initTabs();
    }

    @Override
    public void Query(String query) {
        TabPane tabPane = (TabPane)mainLayout.lookup("#tabPane");

        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        AbstractController controller = tabControllers.get(selectedTab);

    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mainView.fxml"));
            mainLayout = loader.load();

            Scene scene = new Scene(mainLayout);
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initTabs() throws Exception {

        TabPane tabPane = (TabPane)mainLayout.lookup("#tabPane");

        for (Tab tab : tabPane.getTabs()) {
            String id = tab.getId();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/" + id + "View.fxml"));
            AnchorPane content = loader.load();

            tab.setContent(content);

            AbstractController controller = loader.getController();

            tabControllers.put(tab,controller);

            controller.setMainApplication(this);
            controller.setService(service);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
