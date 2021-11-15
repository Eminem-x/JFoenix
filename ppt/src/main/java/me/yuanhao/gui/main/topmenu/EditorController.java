package me.yuanhao.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import me.yuanhao.AppRun;

/**
 * @author Yuanhao
 */
public class EditorController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    public EditorController() {
        judge();
    }

    @FXML
    private void edit() {
        if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 1) {
            Platform.exit();
        } else if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 0) {
            System.out.println(123456);
        }
    }

    @FXML
    private void judge() {
        // disable at start
        if (toolbarPopupList != null) {
            toolbarPopupList.setDisable(!AppRun.stage.isFullScreen());
        }

        // able at showing stage
        AppRun.stage.heightProperty().addListener(observable -> {
            toolbarPopupList.setDisable(!AppRun.stage.isFullScreen());
        });
    }
}
