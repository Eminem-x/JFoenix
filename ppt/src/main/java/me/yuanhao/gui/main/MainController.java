package me.yuanhao.gui.main;

import com.spire.presentation.*;
import com.jfoenix.controls.*;
import fxDrawing.stage.Board;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.Transition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;
import me.yuanhao.mapping.Loader;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

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

        Board board = new Board();
//        drawer.setContent(board.getCanvas());
//
//        Presentation ppt = new Presentation();
//        ppt.loadFromFile("C:\\Users\\DELL\\Desktop\\新建 Microsoft PowerPoint 演示文稿.pptx");

//        BufferedImage bufferedImage = slide.saveAsImage();
//        ImageIO.write(bufferedImage, "PNG",  new File(String.format("extractImage-%1$s.png", 2)));
//
//        Rectangle2D bounds = Screen.getScreens().get(0).getBounds();
//        double width = bounds.getWidth();
//        double height = bounds.getHeight();
//

//        imageView.setFitWidth(width / 1.31);
//        imageView.setFitHeight(height / 1.2);
//
//        board.getCanvas().setPrefWidth(width / 1.31);
//        board.getCanvas().setPrefHeight(height / 1.2);
//
//        board.content.getChildren().add(imageView);
//

        Loader loader = new Loader("C:\\Users\\DELL\\Desktop\\新建 Microsoft PowerPoint 演示文稿.pptx");
        Pane pane = new Pane(
            board.getCanvas(),
            loader.getContent()
        );

        drawer.setContent(pane);
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
