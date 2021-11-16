package me.yuanhao.components;

import com.jfoenix.controls.*;
import javafx.geometry.Orientation;
import javafx.scene.control.ContentDisplay;
import javafx.scene.paint.Color;
import me.yuanhao.AppRun;
import me.yuanhao.draw.stage.Shape;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 * @author Yuanhao
 */
public class EditorNodesList {
    private final JFXNodesList editorNodesList;

    public EditorNodesList() {
        this.editorNodesList = new JFXNodesList();
        init();
    }

    private void init() {
        // 主结点
        JFXButton plusButton = new JFXButton();
        plusButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        plusButton.getStyleClass().add("main-button-nodesList");
        FontIcon fontIcon1 = new FontIcon();
        fontIcon1.setIconLiteral("fas-plus");
        fontIcon1.setIconSize(24);
        fontIcon1.getStyleClass().add("main-icon-nodesList");
        plusButton.setGraphic(fontIcon1);

        // 笔尖大小结点
        JFXNodesList sizeNodesList = new JFXNodesList();
        setSizeNode(sizeNodesList);

        // 颜色选择器结点
        JFXNodesList colorNodesList = new JFXNodesList();
        setColorNode(colorNodesList);

        // 形状选择结点
        JFXNodesList shapeNodesList = new JFXNodesList();
        setShapeNode(shapeNodesList);

        // 封装主结点
        this.editorNodesList.setSpacing(10);
        this.editorNodesList.setRotate(270);
        this.editorNodesList.addAnimatedNode(plusButton);
        this.editorNodesList.addAnimatedNode(sizeNodesList);
        this.editorNodesList.addAnimatedNode(colorNodesList);
        this.editorNodesList.addAnimatedNode(shapeNodesList);
        // translate x 500, translate y -62
        this.editorNodesList.setLayoutX(0);
        this.editorNodesList.setLayoutY(0);

        AppRun.stage.fullScreenProperty().addListener(observable -> {
            this.editorNodesList.setVisible(AppRun.stage.isFullScreen());
        });
    }

    private void setSizeNode(JFXNodesList sizeNodesList) {
        // 笔尖大小结点
        JFXButton sizeButton = new JFXButton();
        sizeButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        sizeButton.getStyleClass().add("animated-option-button-nodesList");
        FontIcon fontIcon2 = new FontIcon();
        fontIcon2.setIconLiteral("fas-font");
        fontIcon2.setIconSize(24);
        fontIcon2.getStyleClass().add("sub-icon-nodesList");
        sizeButton.setGraphic(fontIcon2);

        // 笔尖大小选择器
        JFXSlider jfxSlider = new JFXSlider();
        jfxSlider.setMinHeight(500);
        jfxSlider.setOrientation(Orientation.VERTICAL);
        jfxSlider.valueProperty().addListener(observable -> {
            Shape.rubberSize = (int)jfxSlider.getValue();
        });

        // 封装笔尖大小结点和选择器
        sizeNodesList.setSpacing(10);
        sizeNodesList.getChildren().add(sizeButton);
        sizeNodesList.getChildren().add(jfxSlider);
    }

    private void setColorNode(JFXNodesList colorNodesList) {
        // 颜色选择结点
        JFXButton colorButton = new JFXButton("Color");
        colorButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        colorButton.getStyleClass().add("animated-option-button-nodesList");
        FontIcon fontIcon3 = new FontIcon();
        fontIcon3.setIconLiteral("fas-paint-brush");
        fontIcon3.setIconSize(24);
        fontIcon3.getStyleClass().add("sub-icon-nodesList");
        colorButton.setGraphic(fontIcon3);

        // 颜色选择器
        JFXColorPicker colorPicker = new JFXColorPicker(Color.BLACK);
        colorPicker.getStyleClass().add("button");
        colorPicker.valueProperty().addListener(observable -> {
            Shape.color = colorPicker.getValue();
        });

        // 封装颜色选择结点和选择器
        colorNodesList.setSpacing(10);
        colorNodesList.addAnimatedNode(colorButton);
        colorNodesList.addAnimatedNode(colorPicker);
    }

    private void setShapeNode(JFXNodesList shapeNodesList) {
        JFXButton shapeButton = new JFXButton("Shape");
        shapeButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        shapeButton.getStyleClass().add("animated-option-button-nodesList");
        FontIcon fontIcon4 = new FontIcon();
        fontIcon4.setIconLiteral("fas-circle");
        fontIcon4.setIconSize(24);
        fontIcon4.getStyleClass().add("sub-icon-nodesList");
        shapeButton.setGraphic(fontIcon4);

        JFXToggleButton rectangleToggle= new JFXToggleButton();
        rectangleToggle.setText("Rectangle");
        rectangleToggle.selectedProperty().addListener(observable -> {
            if(rectangleToggle.isSelected()) {
                Shape.toolName = "OVAL";
            } else {
                Shape.toolName = "PEN";
            }
        });
        JFXToggleButton circleToggle = new JFXToggleButton();
        circleToggle.setText("Circle        ");
        JFXToggleButton ovalToggle = new JFXToggleButton();
        ovalToggle.setText("Oval          ");

        shapeNodesList.setSpacing(10);
        shapeNodesList.getChildren().add(shapeButton);
        shapeNodesList.getChildren().add(rectangleToggle);
        shapeNodesList.getChildren().add(circleToggle);
        shapeNodesList.getChildren().add(ovalToggle);
    }

    public JFXNodesList getJfxNodesList() {
        return this.editorNodesList;
    }
}
