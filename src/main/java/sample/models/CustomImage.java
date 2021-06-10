package sample.models;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

public class CustomImage {

    private ImageView image;

    private ImageView imageThumb;

    private StringProperty comment;

    public CustomImage(ImageView img) {
        this.image = img;
    }

    public void setImage(ImageView value) {
        image = value;
    }

    public ImageView getImage() {
        return image;
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
