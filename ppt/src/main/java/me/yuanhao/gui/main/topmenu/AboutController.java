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
        int choice = toolbarPopupList.getSelectionModel().getSelectedIndex();

        if (choice == Function.Login.ordinal()) {

        } else if(choice == Function.Contact.ordinal()) {

        } else if(choice == Function.Exit.ordinal()) {
            Platform.exit();
        }
    }

    enum Function {
        // for easy reading
        Login, Contact, Exit
    }
}
