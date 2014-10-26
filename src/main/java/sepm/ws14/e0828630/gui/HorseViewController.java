package sepm.ws14.e0828630.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import sepm.ws14.e0828630.service.Service;
import sepm.ws14.e0828630.service.ServiceException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

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

    public HorseViewController() {
    }

    @FXML
    private void initialize() {
        horseList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showHorseDetails(newValue));
    }

    @Override
    public void Query(String query) {

    }

    private void showHorseDetails(Horse horse) {

        if (horse != null) {
            // Fill the labels with info from the person     object.
            nameLabel.setText(horse.getName());
            birthdateLabel.setText(horse.getBirthDate().toString(DateTimeFormat.forPattern("MM.dd.yyyy")));
            weightLabel.setText(Double.toString(horse.getWeight()));
            heightLabel.setText(Integer.toString(horse.getHeight()));
            createdLabel.setText(horse.getCreated().toString(DateTimeFormat.forPattern("MM.dd.yyyy mm:hh")));

            Image img = null;

            if (horse.getImage() != null) {
                img = new Image(new ByteArrayInputStream(horse.getImage()));
            }

            imageView.setImage(img);

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
                byte[] bytes = Files.readAllBytes(Paths.get(f.getPath()));

                Horse h = getSelectedHorse();
                h.setImage(bytes);



                Connection c = H2ConnectionFactory.getConnection();
                JdbcHorseDao horseDao = new JdbcHorseDao(c);
                horseDao.update(h);
                c.commit();

                Image img = new Image(new ByteArrayInputStream(bytes));
                imageView.setImage(img);

            } catch (FileNotFoundException e) {

            }
        }
    }

    @FXML
    private void deleteHorse() {
        Horse h = getSelectedHorse();

        if (h == null)
            return;

        try {
            service.deleteHorse(h);
        } catch (ServiceException e) {
            handleServiceException(e);
        }
    }

    private void handleServiceException(ServiceException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getCause().getMessage());
        // todo: log
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

    @Override
    public void setService(Service service) {
        super.setService(service);

       ObservableList list = FXCollections.observableList(service.getHorseList());

        FilteredList<Horse> f = new FilteredList<Horse>(list, p -> true);

        horseList.setItems(f);
    }

}
