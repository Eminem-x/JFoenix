package me.yuanhao.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;

import java.lang.reflect.Method;

/**
 * @author Yuanhao
 */
public class ShowController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    @FXML
    private void show() throws Exception{
        if (toolbarPopupList.getSelectionModel().getSelectedIndex() == Function.ShowFromStart.ordinal()) {

        } else if (toolbarPopupList.getSelectionModel().getSelectedIndex() == Function.ShowFromCurrent.ordinal()) {

        }
    }

    enum Function {
        // for easy reading
        ShowFromStart, ShowFromCurrent
    }
}
