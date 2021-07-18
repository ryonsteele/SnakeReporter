package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sample.HibernateUtil;
import sample.Main;
import sample.models.CodeDTO;
import sample.models.NoteDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class NotesEditController {

    @FXML
    private Button submitButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField textName;
    @FXML
    private TextArea nameField;

    private NoteDTO activeNote;

    private HibernateUtil hibernateUtil = new HibernateUtil();


    @FXML
    private void initialize () {


    }

    void initData(NoteDTO note) {

        if (note != null) {
            textName.setText(note.getName());
            nameField.setText(note.getNote());

            activeNote = (NoteDTO) hibernateUtil.select(NoteDTO.class, note.getId());
        }
    }


    public void handleDefects(ActionEvent actionEvent) {
        //System.exit(0);
    }

    public void handleUpdateButtonAction(ActionEvent actionEvent) {
        System.out.println("Handling submit");
        if (activeNote != null) {
            if (textName.getText().trim().isEmpty()) {
                //todo handle empty name
            } else if (nameField.getText().trim().isEmpty()) {
                //todo handle empty note
            } else {

                activeNote.setName(textName.getText().trim());
                activeNote.setNote(nameField.getText().trim());

                hibernateUtil.update(activeNote);

                Stage stage = (Stage) submitButton.getScene().getWindow();
                stage.close();
            }
        }else{
            activeNote = new NoteDTO();
            activeNote.setName(textName.getText().trim());
            activeNote.setNote(nameField.getText().trim());
            hibernateUtil.insert(activeNote);

            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.close();
        }
    }

    public void handleCancelButtonAction(ActionEvent actionEvent){
        System.out.println("Handling cancel");

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
