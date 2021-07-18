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

public class DefectsController {

    @FXML
    private Button updateButton;
    @FXML
    private Button addButton;
    @FXML
    private ComboBox<CodeDTO> comboDefects;
    @FXML
    private TextArea descField;

    private HibernateUtil hibernateUtil = new HibernateUtil();


    @FXML
    public void initialize () {

        ObservableList<CodeDTO> codes = FXCollections.observableArrayList();
        codes.addAll(loadData());
        comboDefects.getItems().addAll(codes);
        descField.setEditable(false);
        descField.setWrapText(true);
        //TextFields.bindAutoCompletion(comboDefects.getEditor(), comboDefects.getItems());



        Callback<ListView<CodeDTO>, ListCell<CodeDTO>> factory = lv -> new ListCell<CodeDTO>() {

            @Override
            protected void updateItem(CodeDTO item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
                descField.setText(empty ? "" : item.getDesc());
            }

        };

        comboDefects.setCellFactory(factory);
        comboDefects.setButtonCell(factory.call(null));
    }


    public void handleUpdateButtonAction(ActionEvent actionEvent){
        System.out.println("Handling update");
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("../defectsEditStage.fxml"));
            GridPane noteForm = (GridPane) loader.load();
            Stage activeStage = new Stage();
            activeStage.setTitle("Defect Management");

            Scene scene = new Scene(noteForm);
            scene.getRoot().setStyle("-fx-font-family: 'serif'");

            DefectsEditController controller = loader.getController();
            controller.initData(comboDefects.getValue());

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
            alert.setTitle("Defect Deletion");
            alert.setHeaderText("About to delete Defect from System");
            alert.setContentText("Are you sure?");
            alert.setResizable(false);

            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                System.out.println("Ok pressed");
                hibernateUtil.delete(comboDefects.getValue());
                if(comboDefects != null){
                    comboDefects.getItems().clear();
                }
                initialize();
            } else {
                System.out.println("canceled");
                alert.hide();
            }

        }catch(Exception ex){
            System.out.println("Exception deleteDefect");
            ex.printStackTrace();
        }
    }

    public void handleCancelButtonAction(ActionEvent actionEvent){
        System.out.println("Handling cancel");


        Controller ctr = new Controller();
        ctr.initRootLayout();
    }

    private List<CodeDTO> loadData(){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();


            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<CodeDTO> noteCriteria = builder.createQuery(CodeDTO.class);
            Root<CodeDTO> defectRoot = noteCriteria.from(CodeDTO.class);
            noteCriteria.select(defectRoot);

            return session.createQuery(noteCriteria).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
