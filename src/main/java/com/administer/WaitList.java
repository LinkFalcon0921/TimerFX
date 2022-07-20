package com.administer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**Class handle the waiting list in the view of FXML.*/
public class WaitList {
    /**
     * The component in the View
     */
    private final ListView<String> timeListView;
    /**
     * The list inside the component
     */
    private final ObservableList<String> listTimer;
    /**
     * The current value
     */
    private String actualValue;

    public WaitList() {
        listTimer = FXCollections.emptyObservableList();
        timeListView = new ListView(listTimer);
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
        if (listTimer.isEmpty()) {
            return;
        }
        listTimer.remove(0, 1);
        actualValue = listTimer.get(0);
    }

}
