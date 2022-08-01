package com.TimerFX.administer;

import com.TimerFX.notification.facade.NotificationHandler;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;

import java.util.Objects;

/**
 * Class handle the waiting list in the view of FXML.
 */
public class WaitList {


    /**
     * System Notification
     */
    private final NotificationHandler notificationHandler;

    /**
     * The list inside the component
     */
    private final ObservableList<String> listTimer;

    /**
     * Wait List component view
     */
    private final GridPane waitList;
    /**
     * Clock to notify
     */
    private String clockTime;

    public WaitList(GridPane waitList, ObservableList<String> list) {
        this.waitList = waitList;
        this.listTimer = list;
        this.notificationHandler = NotificationHandler.getInstance();
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

    private void notifyPoll() {
        if (Objects.isNull(this.clockTime)) {
            return;
        }

        this.notificationHandler.showAdviceNotification(this.clockTime);
        this.clockTime = null;
    }

    /**
     * Step to the next timer in the list, if exists.
     */
    public void poll() {
        Platform.runLater(() -> {
            this.notifyPoll();
            if (this.isEmpty()) {
                return;
            }
            clockTime = this.listTimer.get(0);
            this.listTimer.remove(clockTime);
        });
    }

    /**
     * Returns true if this waiting list contains no elements.
     */
    public boolean isEmpty() {
        return this.listTimer.isEmpty();
    }

    private void applyListenerView() {
        //  Permuted : list has a size differ to 0.
        this.listTimer.addListener((ListChangeListener<String>) change -> this.waitList.setVisible(!this.isEmpty()));
    }


}
