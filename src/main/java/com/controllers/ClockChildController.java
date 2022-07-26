package com.controllers;

import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClockChildController implements Initializable {

    @FXML
    private Button btnCancel;

    @FXML
    private Label minLabel;

    @FXML
    private Label segLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnCancel.setOnAction( event -> applyCancelAction());
    }

    private void applyCancelAction() {
//        Close the Window
        ((Stage) btnCancel.getScene().getWindow()).hide();
    }

    /**Get the Label that handle the minutes in the clock.*/
    public Label getMinLabel(){
        return this.minLabel;
    }

    /**Get the Label that handle the seconds in the clock.*/
    public Label getSegLabel(){
        return this.segLabel;
    }
}
