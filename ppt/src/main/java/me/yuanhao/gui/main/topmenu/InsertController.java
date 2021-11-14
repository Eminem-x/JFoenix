package me.yuanhao.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import me.yuanhao.mapping.Loader;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Yuanhao
 */
public class InsertController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    @FXML
    private void insert() {
        if (toolbarPopupList.getSelectionModel().getSelectedIndex() == Function.InsertImage.ordinal()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("image", "*.png","*.jpeg","*.jpg"));
            fileChooser.setTitle("打开");
            File img = fileChooser.showOpenDialog(null);

            if(img != null) {
                ImageView imageView = new ImageView("file:" + img.getPath());
                imageView.setFitHeight(300);
                imageView.setFitWidth(250);
                Loader.content.getChildren().add(imageView);
            }
        } else if (toolbarPopupList.getSelectionModel().getSelectedIndex() == Function.InsertText.ordinal()) {

        } else if(toolbarPopupList.getSelectionModel().getSelectedIndex() == Function.InsertShape.ordinal()) {

        }
    }

    enum Function {
        // for easy reading
        InsertImage, InsertText,InsertShape
    }
}
