/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.otemainc.schoolmanager.launcher.ui;

import com.otemainc.schoolmanager.util.SchoolManagerUtil;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Theonlysmartboy <Theonlysmartboy@github.com>
 */
public class SchoolManager extends Application {
    private final static Logger LOGGER = LogManager.getLogger(SchoolManager.class.getName());

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("SCHOOL MANAGER");
            stage.getIcons().add(new Image("/resources/school-manager.png"));
            stage.setResizable(false);
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
     private void closeStage() {
        //stage.close();
    }
  
  public void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../ui/login/login.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Library Assistant");
            stage.setScene(new Scene(parent));
            stage.show();
            SchoolManagerUtil.setStageIcon(stage);
        }
        catch (IOException ex) {
            LOGGER.log(Level.ERROR, "{}", ex);
        }
    }
}
