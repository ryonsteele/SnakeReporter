package sample.models;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

public class CustomFile {

    private ImageView image;

    private String path;

    private StringProperty fileType;

    private ImageView imageThumb;

    private StringProperty comment;

    public CustomFile(ImageView img) {
        this.image = img;
    }
    public CustomFile(String pth) {
        this.path = pth;
    }

    public void setImage(ImageView value) {
        image = value;
    }

    public ImageView getImage() {
        return image;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public StringProperty getFileType() {
        return fileType;
    }

    public void setFileType(StringProperty fileType) {
        this.fileType = fileType;
    }

    public ImageView getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(ImageView imageThumb) {
        this.imageThumb = imageThumb;
    }

    public StringProperty getComment() {
        return comment;
    }

    public void setComment(StringProperty comment) {
        this.comment = comment;
    }
}
