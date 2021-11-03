package me.yuanhao.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * @author Yuanhao
 */
public class AboutController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    @FXML
    private void submit() {
        if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 1) {
            Platform.exit();
        }
    }
}
