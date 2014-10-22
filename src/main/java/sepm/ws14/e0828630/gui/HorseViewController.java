package sepm.ws14.e0828630.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.joda.time.format.DateTimeFormat;
import sepm.ws14.e0828630.dao.DAOException;
import sepm.ws14.e0828630.dao.H2ConnectionFactory;
import sepm.ws14.e0828630.dao.JdbcHorseDao;
import sepm.ws14.e0828630.domain.Horse;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class HorseViewController extends AbstractController {

    @FXML
    private ListView<Horse> horseList;

    @FXML
    private Label weightLabel;

    @FXML
    private Label heightLabel;

    @FXML
    private Label createdLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label birthdateLabel;

    @FXML
    private ImageView imageView;

    private Map<Horse, Image> horseImages;

    public HorseViewController() {
        horseImages = new HashMap<Horse, Image>();
    }

    @FXML
    private void initialize() {
        horseList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showHorseDetails(newValue));


    }


    private void showHorseDetails(Horse horse) {

        if (horse != null) {
            // Fill the labels with info from the person     object.
            nameLabel.setText(horse.getName());
            birthdateLabel.setText(horse.getBirthDate().toString(DateTimeFormat.forPattern("MM.dd.yyyy")));
            weightLabel.setText(Double.toString(horse.getWeight()));
            heightLabel.setText(Integer.toString(horse.getHeight()));
            createdLabel.setText(horse.getCreated().toString(DateTimeFormat.forPattern("MM.dd.yyyy mm:hh")));

            if (horseImages.containsKey(horse)) {
                imageView.setImage(horseImages.get(horse));
            } else {
                imageView.setImage(null);
            }
        } else {
            nameLabel.setText("");
            birthdateLabel.setText("");
            weightLabel.setText("");
            heightLabel.setText("");
            createdLabel.setText("");
            imageView.setImage(null);
        }
    }

    @FXML
    private void handleSetPicture() throws SQLException, DAOException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose picture");
        File f = fileChooser.showOpenDialog(mainApplication.getPrimaryStage());

        if (f != null) {
            try {
FileInputStream fileStream = new FileInputStream(f);

Horse h = getSelectedHorse();
h.setImage(fileStream);
Connection c = H2ConnectionFactory.getConnection();
JdbcHorseDao horseDao = new JdbcHorseDao(c);
horseDao.update(h);
c.commit();


Image img = new Image(fileStream);
horseImages.put(h, img);
imageView.setImage(img);

            } catch (FileNotFoundException e) {

            }
        }
    }

    private Horse getSelectedHorse() {
        return horseList.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleDeletePerson() {
        Horse h = getSelectedHorse();

        if (h != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Do you want to delete this horse?");


            if (alert.showAndWait().get() == ButtonType.OK) {
                horseList.getItems().remove(h);
            }
        }
    }

    public void setMainApplication(MainApplication mainApplication) {
        super.setMainApplication(mainApplication);

        horseList.setItems(mainApplication.getHorseData());

        for (Horse h : horseList.getItems()) {
            if (h.getImage() != null) {
                horseImages.put(h, new Image(h.getImage()));
            }
        }
    }
}
