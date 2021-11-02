package me.yuanhao.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class AboutController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    // close application
    @FXML
    private void submit() {
        if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 1) {
            Platform.exit();
        }
    }
}
