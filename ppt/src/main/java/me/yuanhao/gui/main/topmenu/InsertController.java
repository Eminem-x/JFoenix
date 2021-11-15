package me.yuanhao.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import me.yuanhao.AppRun;
import me.yuanhao.mapping.Loader;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Yuanhao
 */
public class InsertController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    public InsertController() {
        judge();
    }

    @FXML
    private void insert() {
        int choice = toolbarPopupList.getSelectionModel().getSelectedIndex();

        if (choice == Function.InsertImage.ordinal()) {
            insertImg();
        } else if (choice == Function.InsertText.ordinal()) {
            insertText();
        } else if (choice == Function.InsertShape.ordinal()) {
            insertShape();
        }
    }

    @FXML
    private void judge() {
        // disable at showing stage
        AppRun.stage.fullScreenProperty().addListener(observable -> {
            toolbarPopupList.setDisable(AppRun.stage.isFullScreen());
        });
    }

    private void insertImg() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("image", "*.png", "*.jpeg", "*.jpg"));
        fileChooser.setTitle("打开");
        File img = fileChooser.showOpenDialog(null);

        if (img != null) {
            ImageView imageView = new ImageView("file:" + img.getPath());
            imageView.setFitHeight(300);
            imageView.setFitWidth(250);
            Loader.content.getChildren().add(imageView);
        }
    }

    private void insertText() {

    }

    private void insertShape() {

    }

    enum Function {
        // for easy reading
        InsertImage, InsertText, InsertShape
    }
}
