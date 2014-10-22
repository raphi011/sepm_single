package sepm.ws14.e0828630.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sepm.ws14.e0828630.dao.DAOException;
import sepm.ws14.e0828630.dao.H2ConnectionFactory;
import sepm.ws14.e0828630.dao.JdbcHorseDao;
import sepm.ws14.e0828630.domain.Horse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainApplication extends Application {

    private ObservableList<Horse> horseList = FXCollections.observableArrayList();

    private Stage primaryStage;
    private BorderPane mainLayout;

    public ObservableList<Horse> getHorseData() {
        return horseList;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Wendy's riding stable");

        getData();

        initRootLayout();

        initTabs();
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

    public void getData() {
        try {

            JdbcHorseDao horseDao = new JdbcHorseDao(H2ConnectionFactory.getConnection());
            List<Horse> horses = horseDao.readAll();
            horseList.addAll(horses);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch(DAOException e) {
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
            controller.setMainApplication(this);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
