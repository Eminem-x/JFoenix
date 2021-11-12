package me.yuanhao.gui.main.topmenu;

import com.jfoenix.controls.JFXListView;
import com.spire.presentation.ISlide;
import javafx.fxml.FXML;
import me.yuanhao.AppRun;
import me.yuanhao.gui.main.MainController;
import me.yuanhao.mapping.Loader;
import java.lang.reflect.Method;

/**
 * @author Yuanhao
 */
public class ShowController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    private int index = 0;

    @FXML
    private void show() {
        if (toolbarPopupList.getSelectionModel().getSelectedIndex() == Function.ShowFromStart.ordinal()) {
            AppRun.stage.setFullScreen(true);
            Loader.iterator.reset();
            scrollAction(-1);

            MainController.drawer.setOnScroll(event -> {
                if(AppRun.stage.isFullScreen()) {
                    scrollAction(event.getDeltaY());
                }
            });
        } else if (toolbarPopupList.getSelectionModel().getSelectedIndex() == Function.ShowFromCurrent.ordinal()) {
            AppRun.stage.setFullScreen(true);

            MainController.drawer.setOnScroll(event -> {
                try {
                    Class<?> aClass = Class.forName("me.yuanhao.mapping.Loader");
                    Object o = aClass.newInstance();
                    Method method = aClass.getDeclaredMethod("setContent", ISlide.class);
                    method.setAccessible(true);
                    if (Loader.iterator.hasNext()) {
                        method.invoke(o,(ISlide) Loader.iterator.next());
                        index++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void scrollAction(double type) {
        if(type > 0) {
            //向上回滚
        } else if(type < 0) {
            try {
                Class<?> aClass = Class.forName("me.yuanhao.mapping.Loader");
                Object o = aClass.newInstance();
                Method method = aClass.getDeclaredMethod("setContent", ISlide.class);
                method.setAccessible(true);
                if (Loader.iterator.hasNext()) {
                    method.invoke(o,(ISlide) Loader.iterator.next());
                    index++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    enum Function {
        // for easy reading
        ShowFromStart, ShowFromCurrent
    }
}
