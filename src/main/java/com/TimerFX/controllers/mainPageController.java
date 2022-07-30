package com.TimerFX.controllers;

import com.TimerFX.administer.AdminQueue;
import com.TimerFX.util.javafx.IntegerSpinnerValueFactoryUtil;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class mainPageController implements Initializable {

    /*Center view */
    @FXML
    private AnchorPane mainView;

    /**Play button*/
    @FXML
    private Button playActionButton;

    /**Waiting list grid*/
    @FXML
    private GridPane gridWaitList;
    private ObservableList<javafx.scene.Node> gridListNodes;

    @FXML
    private ListView<String> listWait;

    /* Spinners to get the time. */
    @FXML
    private Spinner<Integer> hours_spinner;

    @FXML
    private Spinner<Integer> minute_spinner;

    @FXML
    private Spinner<Integer> second_spinner;

//    Clean the spinner
    @FXML
    private Button btn_resetHour;

    @FXML
    private Button btn_resetMins;

    @FXML
    private Button btn_resetSegs;

/**    Button to show the waiting list*/
    @FXML
    private Button waitListButton;


//    Actions Logic Objects
    private AdminQueue adminLogic;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminLogic = new AdminQueue(
                this.gridWaitList,
                this.listWait.getItems()
                );


//        Set the button action.
        gridListNodes = mainView.getChildren();

//        waitListButton.setOnAction( actionEvent -> switchPane() );

        Integer minH_M = 0, maxH = 10;
        Integer minS = 1, maxM_S = 59;

//        Spinner
        this.hours_spinner.setValueFactory(IntegerSpinnerValueFactoryUtil.getValueFactory(minH_M,maxH));
        this.minute_spinner.setValueFactory(IntegerSpinnerValueFactoryUtil.getValueFactory(minH_M,maxM_S));
        this.second_spinner.setValueFactory(IntegerSpinnerValueFactoryUtil.getValueFactory(minS,maxM_S));

//       Clean the Spinner
        this.btn_resetHour.setOnAction(cleaner -> setValueSpinner(this.hours_spinner,minH_M));
        this.btn_resetMins.setOnAction(cleaner -> setValueSpinner(this.minute_spinner,minH_M));
        this.btn_resetSegs.setOnAction(cleaner -> setValueSpinner(this.second_spinner,minS));

//        Add button action
        this.playActionButton.setOnAction( event -> addTimer());

//        Show waiting list
        waitListButton.setOnAction( event -> switchPane());

    }

    private void setValueSpinner(Spinner spinner,Integer minValue) {
        spinner.getValueFactory().setValue(minValue);
    }

    public void switchPane(){
//        applyAnimation(false);

        if(this.gridListNodes.contains(gridWaitList)){
            this.gridListNodes.remove(gridWaitList);
            this.gridWaitList.setVisible(false);
            return;
        }

        this.gridWaitList.setVisible(true);
        this.gridListNodes.add(gridWaitList);

    }

//    Button action
    private  void addTimer(){
//        hour = hours_spinner.getValue(),
        Integer min = minute_spinner.getValue(),
                seg = second_spinner.getValue();

        adminLogic.add(min,seg);
    }

    private void applyAnimation(boolean isGoingUp) {
        int vMove = isGoingUp ? 200 : ~200 ;
        TranslateTransition transition = new TranslateTransition();

    }
}
