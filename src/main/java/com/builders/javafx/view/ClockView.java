package com.builders.javafx.view;

import com.controllers.ClockChildController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

//Hadle the
public class ClockView {

    private Stage mainStage;

    private ClockChildController controller;
    private final static String FILE_LOCATION = "/fmls/ClockChildView.fxml";

    public ClockView() {
        this.mainStage = new Stage();
    }

    /**Load the view.*/
    public void initiate()  {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(FILE_LOCATION)));
            Parent parent = loader.load();

//            Set the controller
            controller = loader.getController();

//            Set the scene
            Scene scene = new Scene(parent);
            mainStage.initStyle(StageStyle.UNDECORATED);
            mainStage.setScene(scene);

        }catch (NullPointerException | IOException ex){
            ex.printStackTrace();
            System.exit(0);
        }
    }

    public void showView() {
        if(!this.mainStage.isShowing()){
            this.mainStage.show();
        }
    }

    /**Get the Label that handle the minutes in the clock.*/
    public Label getMinLabel(){
        return controller.getMinLabel();
    }

    /**Get the Label that handle the seconds in the clock.*/
    public Label getSegLabel(){
        return controller.getSegLabel();
    }


}
