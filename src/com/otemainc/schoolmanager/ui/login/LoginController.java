/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.otemainc.schoolmanager.ui.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.otemainc.schoolmanager.settings.Preferences;
import com.otemainc.schoolmanager.util.SchoolManagerUtil;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Theonlysmartboy <Theonlysmartboy@github.com>
 */
public class LoginController {

    private final static Logger LOGGER = LogManager.getLogger(LoginController.class.getName());

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    Preferences preference;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String uname = username.getText();
        String pword = DigestUtils.shaHex(password.getText());
        if (uname.equals(preference.getUsername()) && pword.equals(preference.getPassword())) {
            closeStage();
            loadMain();
            LOGGER.log(Level.INFO, "User successfully logged in {}", uname);
        } else {
            username.getStyleClass().add("wrong-credentials");
            password.getStyleClass().add("wrong-credentials");
        }
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }

    private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }

    void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/library/assistant/ui/main/main.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Library Assistant");
            stage.setScene(new Scene(parent));
            stage.show();
            SchoolManagerUtil.setStageIcon(stage);
        } catch (IOException ex) {
            LOGGER.log(Level.ERROR, "{}", ex);
        }
    }

}
