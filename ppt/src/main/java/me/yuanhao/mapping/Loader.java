package me.yuanhao.mapping;

import com.spire.presentation.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;

/**
 * @author Yuanhao
 */
public class Loader {
    private Group content;

    public Loader(String pptName) throws Exception{
        content = new Group();
        load(pptName);
    }

    private void load(String pptName) throws Exception{
        Presentation ppt = new Presentation();
        ppt.loadFromFile(pptName);
        for (int i = 0; i < ppt.getSlides().getCount(); i++) {
            ISlide slide = ppt.getSlides().get(i);
            for(int j = 0; j < slide.getShapes().getCount(); j++) {
                IShape shape = slide.getShapes().get(j);
                if(shape instanceof SlidePicture)
                {
                    SlidePicture pic = (SlidePicture) shape;
                    BufferedImage bufferedImage = pic.getPictureFill().getPicture().getEmbedImage().getImage();
                    ImageView imageView = new ImageView();
                    Image image = SwingFXUtils.toFXImage(bufferedImage,null);
                    imageView.setImage(image);
                    content.getChildren().add(imageView);

                    imageView.setX(pic.getLeft());
                    imageView.setY(pic.getTop());
                    imageView.setFitHeight(pic.getHeight());
                    imageView.setFitWidth(pic.getWidth());
                }
                if(shape instanceof PictureShape)
                {
                    PictureShape ps = (PictureShape) shape;
                    BufferedImage bufferedImage = ps.getEmbedImage().getImage();
                    ImageView imageView = new ImageView();
                    Image image = SwingFXUtils.toFXImage(bufferedImage,null);
                    imageView.setImage(image);
                    content.getChildren().add(imageView);
                }
            }
        }
    }

    public Group getContent() {
        return content;
    }
}
