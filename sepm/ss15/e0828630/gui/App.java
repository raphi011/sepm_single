import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Title");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/main.fxml"));
            VBox mainLayout = loader.load();

            Scene scene = new Scene(mainLayout);
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
