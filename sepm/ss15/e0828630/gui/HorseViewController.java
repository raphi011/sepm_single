import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HorseViewController extends AbstractController {
    private static final Logger log = LogManager.getLogger(JdbcHorseDao.class);

    private final static BooleanProperty EDIT_FORM = new SimpleBooleanProperty(false);

    private Horse selectedHorse;

    @FXML
    private ListView<Horse> horseList;

    @FXML
    private GridPane readHorsePane;
    @FXML
    private Label maxSpeedLabel;
    @FXML
    private Label minSpeedLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private ImageView imageView;

    @FXML
    private GridPane editHorsePane;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField minSpeedTextField;
    @FXML
    private TextField maxSpeedTextField;

    public HorseViewController() {

    }

    @FXML
    private void initialize() {
        horseList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showHorseDetails(newValue) );
    }

    @FXML
    public void edit() throws ServiceException {
        try {
            if (EDIT_FORM.get()) {

                selectedHorse.setMinSpeed(Float.parseFloat(minSpeedTextField.getText()));
                selectedHorse.setMaxSpeed(Float.parseFloat(maxSpeedTextField.getText()));
                selectedHorse.setName(nameTextField.getText());

                if (selectedHorse.getHorseId() != 0) { // existing horse
                    service.updateHorse(selectedHorse);
                } else {
                    service.createHorse(selectedHorse);
                }

                service.updateHorse(selectedHorse);
                showHorseDetails(selectedHorse);
            } else {

                maxSpeedTextField.setText(Float.toString(selectedHorse.getMaxSpeed()));
                minSpeedTextField.setText(Float.toString(selectedHorse.getMinSpeed()));
                nameLabel.setText(selectedHorse.getName());
            }

            EDIT_FORM.set(!EDIT_FORM.get());
        } catch (ServiceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void Query(String query) {

    }

    private void showHorseDetails(Horse horse) {
        selectedHorse = horse;

        if (horse != null) {
            // Fill the labels with info from the person     object.
            nameLabel.setText(horse.getName());
            minSpeedLabel.setText(Float.toString(horse.getMinSpeed()));
            maxSpeedLabel.setText(Float.toString(horse.getMaxSpeed()));

            Image img = null;

            if (horse.getImage() != null) {
                img = new Image(new ByteArrayInputStream(horse.getImage()));
            }

            imageView.setImage(img);

        } else {
            nameLabel.setText("");
            minSpeedLabel.setText("");
            maxSpeedLabel.setText("");
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
                log.error("Picture not found!");
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

        ObservableList list = FXCollections.observableList((List)service.getHorseList());

        FilteredList<Horse> f = new FilteredList<Horse>(list, p -> true);

        horseList.setItems(f);

        setBindings();

    }

    public void setBindings() {
        readHorsePane.visibleProperty().bind(EDIT_FORM);
        editHorsePane.visibleProperty().bind(EDIT_FORM.not());
    }
}

