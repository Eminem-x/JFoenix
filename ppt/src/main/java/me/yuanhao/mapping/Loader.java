package me.yuanhao.mapping;

import com.spire.ms.System.Collections.IEnumerator;
import com.spire.presentation.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.awt.image.BufferedImage;

/**
 * @author Yuanhao
 */
public class Loader {
    public static Group content = new Group();

    private final Group sidePane;

    public static IEnumerator iterator;

    public Loader() {
        sidePane = new Group();
    }

    public Loader(String pptName) throws Exception{
        sidePane = new Group();
        setSidePane(pptName);
    }

    private void setSidePane(String pptName) throws Exception{
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));

        Presentation ppt = new Presentation();
        ppt.loadFromFile(pptName);

        iterator = ppt.getSlides().iterator();
        if(iterator.hasNext()) {
            setContent((ISlide) iterator.next());
            iterator.reset();
        }

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
                    setContent(slide);
                    iterator.reset();
                    while(iterator.hasNext() && !slide.equals((ISlide) iterator.next())) {
                        System.out.println(123);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            vBox.getChildren().add(imageView);
        }

        sidePane.getChildren().add(vBox);
    }

    private void setContent(ISlide slide) throws Exception {
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
            //如何实现ppt整体布局居中 并且能够随着屏幕自动调节大小
        }
    }

    public void test() {
        System.out.println("hello");
    }

    public Group getContent() {
        return content;
    }

    public Group getSidePane() {
        return sidePane;
    }
}
