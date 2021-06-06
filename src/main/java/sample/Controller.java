package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class Controller {

    //Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    //Help Menu button behavior
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("This needs to be filled out");
        alert.setContentText("This needs to be filled out");
        alert.show();
    }
}
