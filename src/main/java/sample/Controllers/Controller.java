package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class Controller {

    @FXML
    private Button submitButton;
    @FXML
    private ComboBox<String> comboNotes = new ComboBox();;

    private BorderPane rootLayout;

    //Exit the program
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    //Help Menu button behavior
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.getDialogPane().setStyle("-fx-font-family: 'serif'");
        alert.setTitle("Program Information");
        alert.setHeaderText("This needs to be filled out");
        alert.setContentText("This needs to be filled out");
        alert.show();
    }

    public void handleNotes(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../notesStage.fxml"));
            GridPane noteForm = (GridPane) loader.load();
            Stage activeStage = Main.getPrimaryStage();
            activeStage.setTitle("Notes Management");

            Scene scene = new Scene(noteForm);
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
            activeStage.setScene(scene);
            activeStage.show();

        }catch(Exception ex){
            System.out.println("Exception handleNotes");
            ex.printStackTrace();
        }
    }

    public void handleDefects(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../defectsStage.fxml"));
            GridPane noteForm = (GridPane) loader.load();
            Stage activeStage = Main.getPrimaryStage();
            activeStage.setTitle("Notes Management");

            Scene scene = new Scene(noteForm);
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
            activeStage.setScene(scene);
            activeStage.show();

        }catch(Exception ex){
            System.out.println("Exception handleNotes");
            ex.printStackTrace();
        }
    }


    public void initRootLayout() {
        try {
            Main.primaryStage.setTitle("Snake Reporter");
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../rootStage.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Second, show the scene containing the root layout.
            Scene scene = new Scene(rootLayout); //We are sending rootLayout to the Scene.
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
            Main.primaryStage.setScene(scene); //Set the scene in primary stage.


            //Third, show the primary stage
            Main.primaryStage.show();
            showTableView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Shows the employee operations view inside the root layout.
    public void showTableView() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../imageTableStage.fxml"));
            AnchorPane tableView = (AnchorPane) loader.load();

            rootLayout.setCenter(tableView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
