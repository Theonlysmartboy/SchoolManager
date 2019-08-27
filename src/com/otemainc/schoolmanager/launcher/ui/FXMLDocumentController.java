/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.otemainc.schoolmanager.launcher.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

/**
 *
 * @author Theonlysmartboy <Theonlysmartboy@github.com>
 */
public class FXMLDocumentController implements Initializable {

    private Task copyWorker;
        @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressIndicator pi;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressBar.setProgress(0);
        pi.setProgress(0);
        copyWorker = createWorker();
        progressBar.progressProperty().unbind();
        pi.progressProperty().unbind();
        progressBar.progressProperty().bind(copyWorker.progressProperty());
        pi.progressProperty().bind(copyWorker.progressProperty());
        new Thread(copyWorker).start();
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i <= 10; i++) {
                    Thread.sleep(1000);
                    //updateMessage("2000 milliseconds");
                    updateProgress(i + 1, 10);
                    if (pi.getProgress() == 1.0) {
                        Platform.runLater(() -> {
                            SchoolManager school = new SchoolManager();
                            school.loadMain();
                        });
                    }
                }
                return true;
            }
        };
    }
}
