package sepm.ws14.e0828630.gui;

import javafx.scene.control.TextField;

public class FxUtils {

    public static double getDoubleFromTextField(TextField textField) {
        String text = textField.getText();
        return Double.parseDouble(text);
    }

    public static int getIntFromTextField(TextField textField) {
        String text = textField.getText();
        return Integer.parseInt(text);
    }
}
