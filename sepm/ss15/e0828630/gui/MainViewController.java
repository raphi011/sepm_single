import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;



public class MainViewController {

    @FXML
    private Label searchHelpLabel;

    @FXML
    private TextField searchTextField;

    @FXML
    private void initialize() {
        searchTextField.textProperty().addListener(
                (observable, oldValue, newValue) -> search(newValue));

        Tooltip tt = new Tooltip();
        tt.setText("a really helpful search tooltip");
        searchHelpLabel.setTooltip(tt);
    }

    private IQuery listener;

    public void setQueryListener(IQuery listener) {
        searchTextField.textProperty().addListener(
                (observable, oldValue, newValue) -> listener.Query(newValue));
    }

    @FXML
    private void search(String query) {

    }

}
