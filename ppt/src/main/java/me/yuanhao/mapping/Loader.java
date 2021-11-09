package me.yuanhao.mapping;

import com.spire.presentation.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import java.awt.image.BufferedImage;

/**
 * @author Yuanhao
 */
public class Loader {
    private final Group content;
    private final Group sidePane;

    public Loader(String pptName) throws Exception{
        content = new Group();
        sidePane = new Group();
        setSidePane(pptName);
    }

    private void setSidePane(String pptName) throws Exception{
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));

        Presentation ppt = new Presentation();
        ppt.loadFromFile(pptName);
        setContent(pptName,ppt.getSlides().get(0));
        for (int i = 0; i < ppt.getSlides().getCount(); i++) {
            ISlide slide = ppt.getSlides().get(i);
            BufferedImage bufferedImage = slide.saveAsImage();
            ImageView imageView = new ImageView();
            Image image = SwingFXUtils.toFXImage(bufferedImage,null);
            imageView.setImage(image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(350);
            imageView.setCursor(Cursor.HAND);
            imageView.setOnMouseClicked(event -> {
                try {
                    setContent(pptName,slide);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            vBox.getChildren().add(imageView);
        }
        sidePane.getChildren().add(vBox);
    }

    private void setContent(String pptName, ISlide slide) throws Exception {
        Presentation ppt = new Presentation();
        ppt.loadFromFile(pptName);
        while(!content.getChildren().isEmpty()) {
            content.getChildren().remove(0);
        }
        for (int j = 0; j < slide.getShapes().getCount(); j++) {
            IShape shape = slide.getShapes().get(j);
            if (shape instanceof SlidePicture) {
                SlidePicture pic = (SlidePicture) shape;
                BufferedImage bufferedImage = pic.getPictureFill().getPicture().getEmbedImage().getImage();
                ImageView imageView = new ImageView();
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imageView.setImage(image);

                imageView.setX(pic.getLeft());
                imageView.setY(pic.getTop());
                imageView.setFitHeight(pic.getHeight());
                imageView.setFitWidth(pic.getWidth());

                //drag simply that need to enhance
                imageView.setOnMouseDragged(event -> {
                    imageView.setX(event.getX());
                    imageView.setY(event.getY());
                });
                content.getChildren().add(imageView);
            }
            if (shape instanceof PictureShape) {
                PictureShape ps = (PictureShape) shape;
                BufferedImage bufferedImage = ps.getEmbedImage().getImage();
                ImageView imageView = new ImageView();
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                imageView.setImage(image);
                content.getChildren().add(imageView);
            }
        }
    }

    public Group getContent() {
        return content;
    }

    public Group getSidePane() {
        return sidePane;
    }
}
