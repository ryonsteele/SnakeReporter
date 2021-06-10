package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import sample.models.CustomImage;
import sample.models.CustomVideo;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImageController {

    @FXML
    private TableView imageTable;
    @FXML
    private TableView videoTable;
    @FXML
    private TableColumn<CustomImage, ImageView> imageColumn;
    @FXML
    private TableColumn<CustomVideo, ImageView> videoColumn;
    @FXML
    private TableColumn<CustomImage, String>  commentImageColumn;
    @FXML
    private TableColumn<CustomVideo, String>  commentVideoColumn;

    //For MultiThreading
    private Executor exec;


    @FXML
    private void initialize () {
        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Employee objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
        (Another option would be to use a PropertyValueFactory, but this is not type-safe

        We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
        */

        //For multithreading: Create executor that uses daemon threads:
        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread (runnable);
            t.setDaemon(true);
            return t;
        });

        imageColumn.setCellValueFactory(new PropertyValueFactory<CustomImage, ImageView>("imageThumb"));
        commentImageColumn.setCellValueFactory(cellData -> cellData.getValue().getComment());

        videoColumn.setCellValueFactory(new PropertyValueFactory<CustomVideo, ImageView>("imageThumb"));
        commentVideoColumn.setCellValueFactory(cellData -> cellData.getValue().getComment());

    }


    //Help Menu button behavior
    public void viewImage(ActionEvent actionEvent) {

    }

    public void searchImages(ActionEvent actionEvent) {

        File dir = new File("/Users/js32938/Desktop/tempFiles/images") ;

        ObservableList<CustomImage> imgList = FXCollections.observableArrayList();
        for(File f : dir.listFiles()) {
            CustomImage item_1 = new CustomImage(new ImageView(new Image(f.toURI().toString())));
            item_1.setImageThumb(new ImageView(new Image(f.toURI().toString(), 200, 200, false, false)));
            item_1.setComment(new SimpleStringProperty("Hello World"));
            imgList.add(item_1);
        }

        imageTable.setItems(imgList);
    }

    public void searchVideos(ActionEvent actionEvent) {

        File dir = new File("/Users/js32938/Desktop/tempFiles/videos") ;

        ObservableList<CustomVideo> imgList = FXCollections.observableArrayList();
        for(File f : dir.listFiles()) {
            CustomVideo item_1 = new CustomVideo(f.toURI().toString());
            item_1.setImageThumb(new ImageView(new Image("playVid.png", 200, 200, false, false)));
            item_1.setComment(new SimpleStringProperty("Hello World"));
            imgList.add(item_1);
        }

        videoTable.setItems(imgList);
    }

//    public void startVideo(Stage primaryStage) throws MalformedURLException {
//        File mediaFile = new File("assets/media/Golden-48569.mp4");
//        Media media = new Media(mediaFile.toURI().toURL().toString());
//
//        MediaPlayer mediaPlayer = new MediaPlayer(media);
//
//        MediaView mediaView = new MediaView(mediaPlayer);
//
//        Scene scene = new Scene(new Pane(mediaView), 1024, 800);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        mediaPlayer.play();
//
//    }
}
