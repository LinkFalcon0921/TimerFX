package com.TimerFX.administer;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;

/**
 * Class handle the waiting list in the view of FXML.
 */
public class WaitList {

    /**
     * The list inside the component
     */
    private final ObservableList<String> listTimer;

    /**
     * Wait List component view
     */
    private final GridPane waitList;
    /*
     * The current value
     */
//    private String actualValue;

    public WaitList(GridPane waitList, ObservableList<String> list) {
        this.waitList = waitList;
        this.listTimer = list;
        applyListenerView();
    }

    /**
     * Append a new timer text inside the wait list.
     */
    public void add(String timer) {
        if (timer.equals("0:0")) {
            return;
        }
        listTimer.add(timer);
    }

    /**
     * Step to the next timer in the list, if exists.
     */
    public void poll() {
        Platform.runLater(() -> {
            if (this.isEmpty()) {
                return;
            }
            this.listTimer.remove(this.listTimer.get(0));
        });
    }

    /**
     * Returns true if this waiting list contains no elements.
     */
    public boolean isEmpty() {
        return this.listTimer.isEmpty();
    }

    private void applyListenerView(){
    //  Permuted : list has a size differ to 0.
        this.listTimer.addListener((ListChangeListener<String>) change -> this.waitList.setVisible(!this.isEmpty()));
    }

}
