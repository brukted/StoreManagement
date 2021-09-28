package com.group_10.storemanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginWindow {

    @FXML
    PasswordField passwordField;
    @FXML
    TextField usernameField;

    @FXML
    protected void onLoginPressed() {

        var userName = usernameField.getText();
        var password = passwordField.getText();
        try {
            var connection = establishConnection(userName,password);
            loadMainView(connection);
        } catch (SQLException ex) {
            var dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Database error");
            dialog.setHeaderText("Can't establish connection to database.");
            dialog.setContentText(ex.getSQLState() + ": " + ex.getMessage());
            dialog.show();
        } catch (IOException ex) {
            var dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Internal Error");
            dialog.setHeaderText(ex.getLocalizedMessage());
            dialog.setContentText(ex.toString());
            dialog.show();
        }

    }

    private void loadMainView(Connection connection) throws IOException {
        Stage stage;
        Scene scene;
        stage = (Stage) passwordField.getScene().getWindow();
        var resource = getClass().getResource("main_window.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        scene = new Scene(fxmlLoader.load(), 960, 540);
        var mainViewController = (MainController) fxmlLoader.getController();
        mainViewController.setConnection(connection);
        stage.setScene(scene);
        stage.show();
    }

    private Connection establishConnection(String userName, String password) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/store_management?"
                + "user=" + userName + "&password=" + password);
        return conn;
    }
}
