package me.yuanhao.gui.main;

import com.jfoenix.controls.*;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javax.annotation.PostConstruct;

/**
 * @author Yuanhao
 */

@ViewController(value = "/fxml/AppRun.fxml", title = "PowerPoint")
public class MainController {
    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;

    @FXML
    private StackPane titleBurgerContainer;
    @FXML
    private JFXHamburger titleBurger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private StackPane optionsBurger;

    @FXML
    private StackPane fileBurger;
    @FXML
    private StackPane insertBurger;
    @FXML
    private StackPane editorBurger;
    @FXML
    private StackPane showBurger;

    private JFXPopup toolbarPopup;
    private JFXPopup fileBarPopup;
    private JFXPopup insertBarPopup;
    private JFXPopup editorBarPopup;
    private JFXPopup showBarPopup;

    @PostConstruct
    public void init() throws Exception {
        // init the title hamburger icon
        final JFXTooltip burgerTooltip = new JFXTooltip("Open drawer");

        drawer.setOnDrawerOpening(e -> {
            final Transition animation = titleBurger.getAnimation();
            burgerTooltip.setText("Close drawer");
            animation.setRate(1);
            animation.play();
        });
        drawer.setOnDrawerClosing(e -> {
            final Transition animation = titleBurger.getAnimation();
            burgerTooltip.setText("Open drawer");
            animation.setRate(-1);
            animation.play();
        });
        titleBurgerContainer.setOnMouseClicked(e -> {
            if (drawer.isClosed() || drawer.isClosing()) {
                drawer.open();
            } else {
                drawer.close();
            }
        });

        JFXTooltip.install(titleBurgerContainer, burgerTooltip, Pos.BOTTOM_CENTER);

        loadController("/fxml/ui/popup/AboutPopup.fxml", "me.yuanhao.gui.main.topmenu.AboutController", toolbarPopup, optionsBurger);
        loadController("/fxml/ui/popup/FilePopup.fxml", "me.yuanhao.gui.main.topmenu.FileController", fileBarPopup, fileBurger);
        loadController("/fxml/ui/popup/InsertPopup.fxml", "me.yuanhao.gui.main.topmenu.InsertController", insertBarPopup, insertBurger);
        loadController("/fxml/ui/popup/EditorPopup.fxml", "me.yuanhao.gui.main.topmenu.EditorController", editorBarPopup, editorBurger);
        loadController("/fxml/ui/popup/ShowPopup.fxml", "me.yuanhao.gui.main.topmenu.ShowController", showBarPopup, showBurger);
    }

    private void loadController(String resource, String controller, JFXPopup popup, StackPane burger) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
        loader.setController(Class.forName(controller).newInstance());
        popup = new JFXPopup(loader.load());

        //pop up the about from left
        String fxmlName = "/fxml/ui/popup/AboutPopup.fxml";
        JFXPopup finalPopup = popup;
        if (fxmlName.equals(resource)) {
            burger.setOnMouseClicked(e ->
                finalPopup.show(burger,
                    JFXPopup.PopupVPosition.TOP,
                    JFXPopup.PopupHPosition.RIGHT,
                    -12,
                    15));
        } else {
            burger.setOnMouseClicked(e ->
                finalPopup.show(burger,
                    JFXPopup.PopupVPosition.TOP,
                    JFXPopup.PopupHPosition.LEFT,
                    -12,
                    15));

        }
        JFXTooltip.setVisibleDuration(Duration.millis(3000));
    }
}
