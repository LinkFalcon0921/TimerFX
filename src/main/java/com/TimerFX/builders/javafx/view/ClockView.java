package com.TimerFX.builders.javafx.view;

import com.TimerFX.controllers.ClockChildController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

//Hadle the
public class ClockView {

    public final Double X_LOCATION_SCREEN = 210D;
    public final Double Y_LOCATION_SCREEN = 160D;

    private Stage mainStage;

    private ClockChildController controller;
    private final static String FILE_LOCATION = "/com/TimerFX/fmls/ClockChildView.fxml";

    public ClockView() {
        this.mainStage = new Stage();
    }

    /**
     * Load the view.
     */
    public void initiate() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(FILE_LOCATION)));
            Parent parent = loader.load();

//            Set the controller
            this.controller = loader.getController();

//            Set the scene
            Scene scene = new Scene(parent);
            this.mainStage.initStyle(StageStyle.UNDECORATED);
            this.mainStage.setScene(scene);

            this.mainStage.setX(this.X_LOCATION_SCREEN);
            this.mainStage.setY(this.Y_LOCATION_SCREEN);

        } catch (NullPointerException | IOException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }

    public void showView() {
        if (!this.mainStage.isShowing()) {
            Platform.runLater(() -> this.mainStage.show());
        }
    }

    public void hideView() {
        if (this.mainStage.isShowing()) {
            Platform.runLater(() -> this.mainStage.close());
        }
    }

    /**
     * Get the Label that handle the minutes in the clock.
     */
    public Label getMinLabel() {
        return controller.getMinLabel();
    }

    /**
     * Get the Label that handle the seconds in the clock.
     */
    public Label getSegLabel() {
        return controller.getSegLabel();
    }


}
