package me.yuanhao.gui.main;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

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

    @PostConstruct
    public void init() throws Exception{

    }
}
