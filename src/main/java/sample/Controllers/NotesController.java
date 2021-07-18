package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Session;
import sample.HibernateUtil;
import sample.Main;
import sample.models.CodeDTO;
import sample.models.NoteDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotesController {

    @FXML
    private Button updateButton;
    @FXML
    private Button addButton;
    @FXML
    private ComboBox<NoteDTO> comboNotes;
    @FXML
    private TextArea nameField;

    private HibernateUtil hibernateUtil = new HibernateUtil();


    @FXML
    public void initialize () {

        ObservableList<NoteDTO> airports = FXCollections.observableArrayList();
        airports.addAll(loadData());
        comboNotes.getItems().addAll(airports);
        nameField.setEditable(false);
        nameField.setWrapText(true);
        //TextFields.bindAutoCompletion(comboNotes.getEditor(), comboNotes.getItems());



        Callback<ListView<NoteDTO>, ListCell<NoteDTO>> factory = lv -> new ListCell<NoteDTO>() {

            @Override
            protected void updateItem(NoteDTO item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
                nameField.setText(empty ? "" : item.getNote());
            }

        };

        comboNotes.setCellFactory(factory);
        comboNotes.setButtonCell(factory.call(null));
    }


    public void handleUpdateButtonAction(ActionEvent actionEvent){
        System.out.println("Handling update");
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../notesEditStage.fxml"));
            GridPane noteForm = (GridPane) loader.load();
            Stage activeStage = new Stage();
            activeStage.setTitle("Notes Management");

            Scene scene = new Scene(noteForm);
            scene.getRoot().setStyle("-fx-font-family: 'serif'");

            NotesEditController controller = loader.getController();
            controller.initData(comboNotes.getValue());

            activeStage.setScene(scene);
            activeStage.show();

        }catch(Exception ex){
            System.out.println("Exception handleNotes");
            ex.printStackTrace();
        }
    }

    public void handleAddButtonAction(ActionEvent actionEvent){
        System.out.println("Handling add");
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../notesEditStage.fxml"));
            GridPane noteForm = (GridPane) loader.load();
            Stage activeStage = new Stage();
            activeStage.setTitle("Notes Management");

            Scene scene = new Scene(noteForm);
            scene.getRoot().setStyle("-fx-font-family: 'serif'");

            NotesEditController controller = loader.getController();
            controller.initData(null);

            activeStage.setScene(scene);
            activeStage.show();

        }catch(Exception ex){
            System.out.println("Exception handleNotes");
            ex.printStackTrace();
        }
    }

    public void handleDeleteButtonAction(ActionEvent actionEvent){
        System.out.println("Handling delete");
        try {

            Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
            alert.getDialogPane().setStyle("-fx-font-family: 'serif'");
            alert.setTitle("Note Deletion");
            alert.setHeaderText("About to delete Note from System");
            alert.setContentText("Are you sure?");
            alert.setResizable(false);

            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                System.out.println("Ok pressed");
                hibernateUtil.delete(comboNotes.getValue());
                if(comboNotes != null){
                    comboNotes.getItems().clear();
                }
                initialize();
            } else {
                System.out.println("canceled");
                alert.hide();
            }

        }catch(Exception ex){
            System.out.println("Exception handleNotes");
            ex.printStackTrace();
        }
    }

    public void handleCancelButtonAction(ActionEvent actionEvent){
        System.out.println("Handling cancel");


        Controller ctr = new Controller();
        ctr.initRootLayout();
    }

    private List<NoteDTO> loadData(){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();


            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<NoteDTO> noteCriteria = builder.createQuery(NoteDTO.class);
            Root<NoteDTO> noteRoot = noteCriteria.from(NoteDTO.class);
            noteCriteria.select(noteRoot);

            return session.createQuery(noteCriteria).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
