package sample.models;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

public class CustomVideo {

    private String path;

    private ImageView imageThumb;

    private StringProperty comment;

    public CustomVideo(String p) {
        this.path = p;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
