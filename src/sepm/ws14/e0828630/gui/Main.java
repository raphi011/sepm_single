package sepm.ws14.e0828630.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sepm.ws14.e0828630.dao.H2Connection;
import sepm.ws14.e0828630.dao.JdbcHorseDao;
import sepm.ws14.e0828630.domain.Horse;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Connection con = H2Connection.openConnection("jdbc:h2:~/test");

        JdbcHorseDao horseDao = new JdbcHorseDao();
//        Horse h = horseDao.read(1);
//        h.setDeleted(true);
//        h.setBirthDate(new Date(System.currentTimeMillis()));
//        horseDao.update(h);
//        con.commit();
        List<Horse> horses = horseDao.search("Abaccus");




        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();





    }


    public static void main(String[] args) {
        launch(args);
    }
}
