package sepm.ws14.e0828630.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

public class MainViewController extends AbstractController {

    @FXML
    private Label searchHelpLabel;



    @FXML
    private void initialize() {
        Tooltip tt = new Tooltip();
        tt.setText("a really helpful search tooltip");
        searchHelpLabel.setTooltip(tt);
    }

}
