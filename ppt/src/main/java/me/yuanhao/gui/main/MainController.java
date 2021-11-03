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
import me.yuanhao.gui.main.topmenu.AboutController;
import me.yuanhao.gui.main.topmenu.FileController;
import me.yuanhao.gui.main.topmenu.InsertController;

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

    @PostConstruct
    public void init() throws Exception{
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

        //init the title pop up in the right item
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui/popup/MainPopup.fxml"));
        loader.setController(new AboutController());
        toolbarPopup = new JFXPopup(loader.load());

        optionsBurger.setOnMouseClicked(e ->
            toolbarPopup.show(optionsBurger,
                JFXPopup.PopupVPosition.TOP,
                JFXPopup.PopupHPosition.RIGHT,
                -12,
                15));
        JFXTooltip.setVisibleDuration(Duration.millis(3000));
        JFXTooltip.install(titleBurgerContainer, burgerTooltip, Pos.BOTTOM_CENTER);

        FXMLLoader fileLoader = new FXMLLoader(getClass().getResource("/fxml/ui/popup/FilePopup.fxml"));
        fileLoader.setController(new FileController());
        toolbarPopup = new JFXPopup(fileLoader.load());

        fileBurger.setOnMouseClicked(e -> {
                toolbarPopup.show(fileBurger,
                    JFXPopup.PopupVPosition.TOP,
                    JFXPopup.PopupHPosition.LEFT,
                    -12,
                    15);
        });
        JFXTooltip.setVisibleDuration(Duration.millis(3000));

        FXMLLoader insertLoader = new FXMLLoader(getClass().getResource("/fxml/ui/popup/InsertPopup.fxml"));
        insertLoader.setController(new InsertController());
        toolbarPopup = new JFXPopup(insertLoader.load());

        insertBurger.setOnMouseClicked(e ->
            toolbarPopup.show(insertBurger,
                JFXPopup.PopupVPosition.TOP,
                JFXPopup.PopupHPosition.LEFT,
                -12,
                15));
        JFXTooltip.setVisibleDuration(Duration.millis(3000));
    }
}
