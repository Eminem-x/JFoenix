package me.yuanhao.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class InsertController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    // close application
    @FXML
    private void insert() {
        if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 1) {
            Platform.exit();
        } else if(toolbarPopupList.getSelectionModel().getSelectedIndex() == 0) {
            System.out.println(123456);
        }
    }
}
