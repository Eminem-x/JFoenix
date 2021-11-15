package me.yuanhao.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import me.yuanhao.AppRun;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @author Yuanhao
 */
public class FileController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    public FileController() {
        judge();
    }

    @FXML
    private void file() throws Exception{
        int choice = toolbarPopupList.getSelectionModel().getSelectedIndex();

        if (choice == Function.NewFile.ordinal()) {
            newFile();
        } else if (choice == Function.Save.ordinal()) {
            saveFile();
        } else if (choice == Function.Open.ordinal()) {
            openFile();
        } else if (choice == Function.SaveAs.ordinal()) {
            saveAsFile();
        }
    }

    @FXML
    private void judge() {
        // disable at showing stage
        AppRun.stage.heightProperty().addListener(observable -> {
            toolbarPopupList.setDisable(AppRun.stage.isFullScreen());
        });
    }

    private void newFile() throws Exception{
        Class<?> aClass = Class.forName("me.yuanhao.gui.main.MainController");
        Object o = aClass.newInstance();
        Method method = aClass.getDeclaredMethod("setDrawer", String.class);
        method.setAccessible(true);
        method.invoke(o,"D:\\java\\JFoenix\\ppt\\src\\main\\resources\\新建 Microsoft PowerPoint 演示文稿.pptx");
    }

    private void saveFile() {

    }

    private void openFile() throws Exception{
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PPT", "*.pptx","*.ppt"));
        fileChooser.setTitle("打开");
        File img = fileChooser.showOpenDialog(null);

        Class<?> aClass = Class.forName("me.yuanhao.gui.main.MainController");
        Object o = aClass.newInstance();
        Method method = aClass.getDeclaredMethod("setDrawer", String.class);
        method.setAccessible(true);
        if(img != null) {
            method.invoke(o,img.getAbsolutePath());
        }
    }

    private void saveAsFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PPT", "*.pptx"));
        fileChooser.setTitle("保存");
        File img = fileChooser.showSaveDialog(null);
    }

    enum Function {
        // for easy reading
        NewFile,Save,Open,SaveAs
    }
}
