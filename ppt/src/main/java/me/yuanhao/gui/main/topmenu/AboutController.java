package me.yuanhao.gui.main.topmenu;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.net.URI;
import java.sql.*;

/**
 * @author Yuanhao
 */
@SuppressWarnings("all")
public class AboutController {
    @FXML
    private JFXListView<?> toolbarPopupList;

    private static final String FX_LABEL_FLOAT_TRUE = "-fx-label-float:true;";
    private static final String ERROR = "error";

    private String account;
    private String password;

    @FXML
    private void submit() {
        int choice = toolbarPopupList.getSelectionModel().getSelectedIndex();

        if (choice == Function.Login.ordinal()) {
            login();
        } else if (choice == Function.Contact.ordinal()) {
            try {
                Desktop.getDesktop().browse(new URI("https://eminem-x.github.io/"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (choice == Function.Exit.ordinal()) {
            Platform.exit();
        }
    }

    private void login() {
        VBox pane = new VBox();
        pane.setSpacing(30);
        pane.setStyle("-fx-background-color:WHITE;-fx-padding:40;");

        Stage stage = new Stage();
        Scene scene = new Scene(pane, 400, 300, Color.WHITE);
        scene.getStylesheets()
            .add(AboutController.class.getResource("/css/jfoenix-components.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login");

        JFXTextField accountField = new JFXTextField();
        accountField.setPromptText("Account");
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");
        FontIcon warnIcon = new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE);
        warnIcon.getStyleClass().add(ERROR);
        validator.setIcon(warnIcon);
        accountField.getValidators().add(validator);
        accountField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                accountField.validate();
            }
        });

        pane.getChildren().add(accountField);

        JFXPasswordField passwordField = new JFXPasswordField();
        passwordField.setStyle(FX_LABEL_FLOAT_TRUE);
        passwordField.setPromptText("Password");
        validator = new RequiredFieldValidator();
        validator.setMessage("Password Can't be empty");
        warnIcon = new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE);
        warnIcon.getStyleClass().add(ERROR);
        validator.setIcon(warnIcon);
        passwordField.getValidators().add(validator);
        passwordField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                passwordField.validate();
            }
        });
        pane.getChildren().add(passwordField);

        JFXButton loginButton = new JFXButton();
        loginButton.setText("Login");
        loginButton.getStyleClass().add("button-raised");

        loginButton.setOnMouseClicked(event -> {
            this.account = accountField.getText();
            this.password = passwordField.getText();
            JFXAlert jfxAlert = new JFXAlert();
            jfxAlert.setAnimation(JFXAlertAnimation.TOP_ANIMATION);
            jfxAlert.setTitle("Tips");
            jfxAlert.setSize(200, 150);
            jfxAlert.setResizable(false);
            if (handleLogin()) {
                jfxAlert.setContent(new Label("Succeed!"));
                jfxAlert.show();
            } else if (!"".equals(this.account) && !"".equals(this.password)) {
                jfxAlert.setContent(new Label("Error!"));
                jfxAlert.show();
            }
        });
        pane.getChildren().add(loginButton);

        stage.show();
    }

    private boolean handleLogin() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            if (this.account == null || this.password == null) {
                return false;
            }
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/powerpoint", "root", "177399jyx");
            String sql = "select * from user where username = ? and password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    enum Function {
        // for easy reading
        Login, Contact, Exit
    }
}
