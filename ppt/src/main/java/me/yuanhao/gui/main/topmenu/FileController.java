package me.yuanhao.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @author Yuanhao
 */
public class FileController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    @FXML
    private void file() throws Exception{
        int choice = toolbarPopupList.getSelectionModel().getSelectedIndex();
        if (choice == Function.NewFile.ordinal()) {

        } else if (choice == Function.Save.ordinal()) {

        } else if (choice == Function.Open.ordinal()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PPT", "*.pptx","*.ppt"));
            fileChooser.setTitle("打开");
            File img = fileChooser.showOpenDialog(null);

            Class<?> aClass = Class.forName("me.yuanhao.gui.main.MainController");
            Object o = aClass.newInstance();
            Method method = aClass.getMethod("setDrawer", String.class);
            method.invoke(o,img.getAbsolutePath());
        } else if (choice == Function.SaveAs.ordinal()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PPT", "*.pptx"));
            fileChooser.setTitle("保存");
            File img = fileChooser.showSaveDialog(null);
        }
    }

    enum Function {
        // for easy reading
        NewFile,Save,Open,SaveAs
    }
}
