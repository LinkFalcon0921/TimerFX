package com.TimerFX.administer;

import com.TimerFX.builders.ClockerBuilder;
import com.TimerFX.builders.TimerBuilder;
import com.TimerFX.builders.javafx.view.ClockView;
import com.TimerFX.util.Labels;
import com.TimerFX.util.timer.TimerDefaultTask;
import com.TimerFX.util.timer.TimerSchedule;
import com.TimerFX.worker.Clockers;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Objects;

/**
 * <i>SINGLETON CLASS<i/>, that handle the interaction with the view components and communicate with the logical part.
 */
public class AdminQueue {

    private AdminAction actionEndTimer;

    private TimerSchedule actualClock;
    /**
     * Labels handler
     */
    private final Labels labels;

    /**
     * Waiting list handler
     */
    private final WaitList waitList;

    /**
     * View of the chronometer
     */
    private final ClockView clockView;

    public AdminQueue(GridPane waitListGrid, ObservableList<String> list) {
//        create the chronometer view
        this.clockView = new ClockView();
        this.clockView.initiate();

//        Set the label Admin
        Label minLabel = clockView.getMinLabel(), segLabel = clockView.getSegLabel();

        this.labels = new Labels(minLabel, segLabel);

        this.waitList = new WaitList(waitListGrid, list);

//        Set the action between Timers
        this.setEndAction();

    }

    public void add(Integer min, Integer seg) {

//        Add the time in the new timer
        final Clockers timerInQueue = ClockerBuilder.getDefaultBuilder().createDefaultClock(min, seg).get();

        final TimerDefaultTask timerClocker = TimerBuilder.getDefaultBuilder().createDefaultTimer(this.labels, timerInQueue, this.actionEndTimer).get();

        if (this.add(timerClocker)) {
            //Add in the waiting list
            this.waitList.add(timerInQueue.toString());
            this.actionEndTimer.next();
        }

    }

    //    TODO Check waitList Interaction
    /*Verify and put the clock in the Queue*/
    private boolean add(TimerSchedule newClock) {
        if (Objects.isNull(actualClock)) {
            this.actualClock = newClock;
            this.waitList.poll();
            return true;
        }

        actualClock.setNext(newClock);
        return true;
    }

    /**
     * Add the action to switch between Timers.
     */
    private void setEndAction() {

        this.actionEndTimer = () -> {

            if (this.actualClock.hasEnded()) {
                this.actualClock = this.actualClock.getNext();
                this.waitList.poll();
            }

            if (Objects.isNull(this.actualClock)) {
                this.clockView.hideView();
                return;
            }

            this.actualClock.start();

            //Show the clock
            this.clockView.showView();
        };
    }

}
