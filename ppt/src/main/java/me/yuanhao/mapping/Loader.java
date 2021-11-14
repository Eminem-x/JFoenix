package me.yuanhao.mapping;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.validation.RequiredFieldValidator;
import com.spire.ms.System.Collections.IEnumerator;
import com.spire.presentation.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import me.yuanhao.AppRun;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

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

    public Loader(String pptName) throws Exception {
        sidePane = new Group();
        setSidePane(pptName);
    }

    /**
     * set the drawer's sidePane which is the ppt' view
     * @param pptName url of the ppt
     */
    private void setSidePane(String pptName) throws Exception {
        // ppt预览图垂直排列
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));

        // 从指定文件中读取ppt
        Presentation ppt = new Presentation();
        ppt.loadFromFile(pptName);

        // 用迭代器操作ppt每一页 与此同时映射第一页
        iterator = ppt.getSlides().iterator();
        if (iterator.hasNext()) {
            setContent((ISlide) iterator.next());
            iterator.reset();
        }

        // ppt的每一页以图片形式映射到预览图
        for (int i = 0; i < ppt.getSlides().getCount(); i++) {
            ISlide slide = ppt.getSlides().get(i);

            BufferedImage bufferedImage = slide.saveAsImage();
            ImageView imageView = new ImageView();
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);

            imageView.setFitHeight(200);
            imageView.setFitWidth(350);

            // 选取图片时触发事件 将指定的页元素映射到content展示以及操作
            imageView.setOnMouseClicked(event -> {
                try {
                    setContent(slide);

                    // 更新迭代器的位置
                    iterator.reset();
                    while (iterator.hasNext() && !slide.equals((ISlide) iterator.next())) {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            vBox.getChildren().add(imageView);
        }

        sidePane.getChildren().add(vBox);
    }

    /**
     * set the drawer's content which is the ppt show
     * @param slide the specific slide
     */
    private void setContent(ISlide slide) throws Exception {
        // 清空先前的元素
        while (!content.getChildren().isEmpty()) {
            content.getChildren().remove(0);
        }

        // 按照元素的不同形式进行不同的映射操作
        for (int j = 0; j < slide.getShapes().getCount(); j++) {
            IShape shape = slide.getShapes().get(j);
            if (shape instanceof SlidePicture) {
                setSidePicture(shape);
            } else if (shape instanceof PictureShape) {
                setPictureShape(shape);
            } else if (shape instanceof IAutoShape) {
                setIAutoShape(shape);
            }
        }
    }

    private void setSidePicture(IShape shape) throws Exception {
        SlidePicture pic = (SlidePicture) shape;
        BufferedImage bufferedImage = pic.getPictureFill().getPicture().getEmbedImage().getImage();
        ImageView imageView = new ImageView();
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        imageView.setImage(image);

        // 使图片处于布局中心位置
        imageView.setLayoutX((pic.getLeft() / 0.7) + 48);
        imageView.setLayoutY((pic.getTop() / 0.7) + 16);
        imageView.setFitHeight(pic.getHeight() / 0.7);
        imageView.setFitWidth(pic.getWidth() / 0.7);

        // 使图片随着界面大小等比变化
        setImageAuto(imageView);

        // 拖动图片移动操作
        imageView.setOnMousePressed(event -> {
            final double deltaX = event.getX() - imageView.getX();
            final double deltaY = event.getY() - imageView.getY();
            imageView.setOnMouseDragged(e -> {
                imageView.setX(e.getX() - deltaX);
                imageView.setY(e.getY() - deltaY);
            });
        });

        content.getChildren().add(imageView);
    }

    private void setPictureShape(IShape shape) throws Exception {
        PictureShape ps = (PictureShape) shape;
        BufferedImage bufferedImage = ps.getEmbedImage().getImage();
        ImageView imageView = new ImageView();
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        imageView.setImage(image);
        content.getChildren().add(imageView);
    }

    private void setIAutoShape(IShape shape) throws Exception {
        IAutoShape iAutoShape = (IAutoShape) shape;
        String text = iAutoShape.getTextFrame().getText();
        System.out.println(iAutoShape.getTextFrame().getTextStyle());

        JFXTextArea jfxTextArea = new JFXTextArea(text);
        jfxTextArea.setPromptText("文本框区域:");
        jfxTextArea.setLabelFloat(true);
        jfxTextArea.setLayoutX(iAutoShape.getTextFrame().getTextLocation().getX());
        jfxTextArea.setLayoutY(iAutoShape.getTextFrame().getTextLocation().getY());

        RequiredFieldValidator validator = new RequiredFieldValidator();
        // NOTE adding error class to text area is causing the cursor to disappear
        validator.setMessage("空文本框！");
        FontIcon warnIcon = new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE);
        warnIcon.getStyleClass().add("error");
        validator.setIcon(warnIcon);
        jfxTextArea.getValidators().add(validator);
        jfxTextArea.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                jfxTextArea.validate();
            }
        });
        //如何选取平移
//                jfxTextArea.setOnMouseDragged(event -> {
//                    jfxTextArea.setLayoutX(event.getX());
//                    jfxTextArea.setLayoutY(event.getY());
//                });

        //该如何设置离开时关闭区域

        content.getChildren().add(jfxTextArea);
    }

    private void setImageAuto(ImageView imageView) {
        AppRun.stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            double f = newValue.doubleValue() / oldValue.doubleValue();
            imageView.setFitHeight(f * imageView.getFitHeight());
            imageView.setLayoutX(f * imageView.getLayoutX());
        });
        AppRun.stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            double f = newValue.doubleValue() / oldValue.doubleValue();
            imageView.setFitWidth(f * imageView.getFitWidth());
            imageView.setLayoutY(f * imageView.getLayoutY());
        });
    }

    public Group getContent() {
        return content;
    }

    public Group getSidePane() {
        return sidePane;
    }
}
