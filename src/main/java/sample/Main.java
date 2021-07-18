package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sample.Controllers.Controller;
import sample.models.CodeDTO;
import sample.models.NoteDTO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {

    //This is our PrimaryStage (It contains everything)
    public static Stage primaryStage;

    private void setPrimaryStage(Stage stage) {
        Main.primaryStage = stage;
    }

    static public Stage getPrimaryStage() {
        return Main.primaryStage;
    }

    //This is the BorderPane of RootLayout
    private BorderPane rootLayout;

    public static HashMap<String,CodeDTO> map=new HashMap<String,CodeDTO>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Declare a primary stage (Everything will be on this stage)
        Main.primaryStage = primaryStage;

        //dataLoaderCheck();

        Controller ctrl = new Controller();
        ctrl.initRootLayout();
    }



    private void dataLoaderCheck(){

        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
//            // start a transaction
//            transaction = session.beginTransaction();
//            // save the student objects
//            map.values().forEach(r -> session.save(r));
//
//            // commit transaction
//            transaction.commit();


            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CodeDTO> criteria = builder.createQuery(CodeDTO.class);

            Root<CodeDTO> variableRoot = criteria.from(CodeDTO.class);
            criteria.select(variableRoot);

            List< CodeDTO > codes = session.createQuery(criteria).getResultList();
            System.out.println("Defects Record count: " + codes.size());
            //codes.forEach(s -> System.out.println(s.getName()));


            CriteriaQuery<NoteDTO> noteCriteria = builder.createQuery(NoteDTO.class);
            Root<NoteDTO> noteRoot = noteCriteria.from(NoteDTO.class);
            noteCriteria.select(noteRoot);

            List< NoteDTO > notes = session.createQuery(noteCriteria).getResultList();
            System.out.println("Note Record count: " + notes.size());
            //notes.forEach(s -> System.out.println(s.getName()));

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
