package me.yuanhao.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * @author Yuanhao
 */
public class ShowController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    @FXML
    private void show() {
        if (toolbarPopupList.getSelectionModel().getSelectedIndex() == Function.ShowFromStart.ordinal()) {

        } else if (toolbarPopupList.getSelectionModel().getSelectedIndex() == Function.ShowFromCurrent.ordinal()) {

        }
    }

    enum Function {
        // for easy reading
        ShowFromStart,ShowFromCurrent
    }
}
