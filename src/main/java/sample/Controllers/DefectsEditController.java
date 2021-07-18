package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.HibernateUtil;
import sample.models.CodeDTO;
import sample.models.NoteDTO;

public class DefectsEditController {

    @FXML
    private Button submitButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField textName;
    @FXML
    private TextArea defectField;

    private CodeDTO activeNote;

    private HibernateUtil hibernateUtil = new HibernateUtil();


    @FXML
    private void initialize () {


    }

    void initData(CodeDTO note) {

        if (note != null) {
            textName.setText(note.getName());
            defectField.setText(note.getDesc());

            activeNote = (CodeDTO) hibernateUtil.select(CodeDTO.class, note.getId());
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
            } else if (defectField.getText().trim().isEmpty()) {
                //todo handle empty note
            } else {

                activeNote.setName(textName.getText().trim());
                activeNote.setDesc(defectField.getText().trim());

                hibernateUtil.update(activeNote);

                Stage stage = (Stage) submitButton.getScene().getWindow();
                stage.close();
            }
        }else{
            activeNote = new CodeDTO();
            activeNote.setName(textName.getText().trim());
            activeNote.setDesc(defectField.getText().trim());
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
