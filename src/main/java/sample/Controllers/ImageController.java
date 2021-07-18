package sample.Controllers;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import sample.HibernateUtil;
import sample.models.CodeDTO;
import sample.models.CustomFile;
import sample.models.NoteDTO;

import java.io.File;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImageController {

    @FXML
    private TableView fileTable;
    @FXML
    private TableColumn<CustomFile, ImageView> imageColumn;
    @FXML
    private TableColumn<CustomFile, String> fileTypeColumn;
    @FXML
    private TableColumn<CustomFile, String>  commentColumn;
    @FXML
    private ContextMenu contextMenu;

    private ComboBox<CodeDTO> comboDefects = new ComboBox<>();
    private ComboBox<NoteDTO> comboNotes = new ComboBox<>();

    private DirectoryChooser sourceDirectoryChooser;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Boolean isPlaying = false;
    private Button playPausebtn = new Button("Pause");
    //For MultiThreading
    private Executor exec;
    private HibernateUtil hibernateUtil = new HibernateUtil();




    @FXML
    private void initialize () {

        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread (runnable);
            t.setDaemon(true);
            return t;
        });

        sourceDirectoryChooser = new DirectoryChooser();
        imageColumn.setCellValueFactory(new PropertyValueFactory<CustomFile, ImageView>("imageThumb"));
        fileTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getFileType());
        commentColumn.setCellValueFactory(cellData -> cellData.getValue().getComment());

        playPausebtn.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                pauseResume();
            }
        });

        fileTable.setRowFactory((Callback<TableView<CustomFile>, TableRow<CustomFile>>) tableView -> {
                    final TableRow<CustomFile> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    MenuItem editItem = new MenuItem("View");
                    editItem.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            startVideo((CustomFile) fileTable.getItems().get(row.getIndex()));
                        }
                    });
                    MenuItem removeItem = new MenuItem("Delete");
                    removeItem.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            fileTable.getItems().remove(row.getItem());
                        }
                    });
                    rowMenu.getItems().addAll(editItem, removeItem);

                    // only display context menu for non-empty rows:
                    row.contextMenuProperty().bind(
                            Bindings.when(row.emptyProperty())
                                    .then((ContextMenu) null)
                                    .otherwise(rowMenu));
                    return row;
                });
    }


    public void searchFiles(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Window theStage = source.getScene().getWindow();
        ObservableList<CustomFile> fileList = FXCollections.observableArrayList();
        File imageDir = null;
        File videoDir = null;

        File selectedDir = sourceDirectoryChooser.showDialog(theStage);
        if(selectedDir == null) return;

        for(File f : selectedDir.listFiles()){
            if(f.isDirectory() && f.getName().equalsIgnoreCase("images")){
                imageDir = f;
            }
            if(f.isDirectory() && f.getName().equalsIgnoreCase("videos")){
                videoDir = f;
            }
        }

        if(imageDir != null && imageDir.isDirectory()) {
            for (File f : imageDir.listFiles()) {
                CustomFile item_1 = new CustomFile(new ImageView(new Image(f.toURI().toString(), 600, 600, true, false)));
                item_1.setImageThumb(new ImageView(new Image(f.toURI().toString(), 100, 100, false, false)));
                item_1.setComment(new SimpleStringProperty("Hello World"));
                item_1.setFileType(new SimpleStringProperty("Image"));
                fileList.add(item_1);
            }
        }

        if(videoDir != null && videoDir.isDirectory()) {
            for (File f : videoDir.listFiles()) {
                CustomFile item_1 = new CustomFile(f.toURI().toString());
                item_1.setImageThumb(new ImageView(new Image("playVid.png", 100, 100, false, false)));
                item_1.setComment(new SimpleStringProperty("Hello World"));
                item_1.setFileType(new SimpleStringProperty("Video"));
                fileList.add(item_1);
            }
        }

        fileTable.setItems(fileList);
    }

    public void startVideo(CustomFile file) {
            Parent root;
            try {

                ObservableList<CodeDTO> codes = FXCollections.observableArrayList();
                codes.addAll((List<CodeDTO>)(List<?>) hibernateUtil.selectAll(CodeDTO.class));
                comboDefects.getItems().addAll(codes);


                Callback<ListView<CodeDTO>, ListCell<CodeDTO>> factory = lv -> new ListCell<CodeDTO>() {
                    @Override
                    protected void updateItem(CodeDTO item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? "" : item.getName());
                    }
                };

                comboDefects.setCellFactory(factory);
                comboDefects.setButtonCell(factory.call(null));

                ObservableList<NoteDTO> notes = FXCollections.observableArrayList();
                notes.addAll((List<NoteDTO>)(List<?>) hibernateUtil.selectAll(NoteDTO.class));
                comboNotes.getItems().addAll(notes);


                Callback<ListView<NoteDTO>, ListCell<NoteDTO>> factoryNotes = lv -> new ListCell<NoteDTO>() {
                    @Override
                    protected void updateItem(NoteDTO item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? "" : item.getName());
                    }
                };

                comboNotes.setCellFactory(factoryNotes);
                comboNotes.setButtonCell(factoryNotes.call(null));


                Stage stage = new Stage();
                stage.setTitle("Viewer");

                if(file.getFileType().isEqualToIgnoreCase("Video").getValue()) {
                    isPlaying = true;
                    media = new Media(file.getPath());
                    mediaPlayer = new MediaPlayer(media);
                    MediaView mediaView = new MediaView(mediaPlayer);

                    VBox stack = new VBox();
                    stack.getChildren().add(mediaView);
                    stack.getChildren().add(playPausebtn);
                    stack.getChildren().add(comboNotes);
                    stack.getChildren().add(comboDefects);
                    Scene scene = new Scene(stack, 700, 500);
                    scene.getRoot().setStyle("-fx-font-family: 'serif'");
                    stage.setScene(scene);
                    stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::stop);
                    stage.show();

                    mediaPlayer.play();

                }else{
                    VBox stack = new VBox();
                    stack.getChildren().add(new Pane(file.getImage()));
                    stack.getChildren().add(comboNotes);
                    stack.getChildren().add(comboDefects);
                    Scene scene = new Scene(stack, 700, 500);
                    scene.getRoot().setStyle("-fx-font-family: 'serif'");
                    stage.setScene(scene);
                    stage.show();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
    }


    public void pauseResume() {
        new Thread(new Task<>() {
            @Override
            protected Object call() throws Exception {
                if(mediaPlayer.getStatus().equals(MediaPlayer.Status.PAUSED))
                {
                    isPlaying = true;
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            playPausebtn.setText("Pause");
                        }
                    });
                    //dash.musicPanePlayPauseButtonImageView.setImage(dash.pauseIcon);
                    mediaPlayer.play();
                }
                else if(mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING))
                {
                    isPlaying = false;
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            playPausebtn.setText("Play");
                        }
                    });
                    //dash.musicPanePlayPauseButtonImageView.setImage(dash.playIcon);
                    mediaPlayer.pause();
                }
                return null;
            }
        }).start();
    }

    public void stop(WindowEvent ev)
    {
        System.out.println("Stop Method");
        if(isPlaying)
        {
            isPlaying = false;
            try
            {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }
            catch (Exception e)
            {
                System.out.println("disposing ...");
            }
        }
    }

}
