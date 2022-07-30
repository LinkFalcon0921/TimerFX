package com.TimerFX.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class AppInit extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            String LOCATION = "/com/TimerFX/fmls/mainPage.fxml";
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(LOCATION)));
            Scene scene = new Scene(parent,500,500);
            stage.setScene(scene);

            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }
}
