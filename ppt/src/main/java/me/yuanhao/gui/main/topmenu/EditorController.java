package me.yuanhao.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * @author Yuanhao
 */
public class EditorController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    @FXML
    private void edit() {
        if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 1) {
            Platform.exit();
        } else if(toolbarPopupList.getSelectionModel().getSelectedIndex() == 0) {
            System.out.println(123456);
        }
    }
}
