package com.administer;

import com.builders.ClockerBuilder;
import com.builders.TimerBuilder;
import com.builders.javafx.view.ClockView;
import com.util.Labels;
import com.util.timer.TimerDefaultTask;
import com.util.timer.TimerSchedule;
import com.worker.Clockers;
import javafx.scene.control.Label;

import java.util.Objects;

//TODO USE in the initilize controller

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

    public AdminQueue() {
//        create the chronometer view
        clockView = new ClockView();
        clockView.initiate();

//        Set the label Admin
        Label minLabel = clockView.getMinLabel(),
                segLabel = clockView.getSegLabel();

        labels = new Labels(minLabel, segLabel);

        waitList = new WaitList();

//        Set the action between Timers
        this.setEndAction();

    }



    public void add(Integer min, Integer seg) {

//        Add the time in the new timer
        final Clockers timerInQueue = ClockerBuilder.getDefaultBuilder()
                .createDefaultClock(min, seg).get();

        TimerDefaultTask timerClocker = TimerBuilder.getDefaultBuilder()
                .createDefaultTimer(this.labels, timerInQueue, this.actionEndTimer).get();

        if (this.add(timerClocker)) {
            //Add in the waiting list
            waitList.add(timerInQueue.toString());
            this.actionEndTimer.next();
        }

    }

    /*Verify and put the clock in the Queue*/
    private boolean add(TimerDefaultTask newClock) {
        if (Objects.isNull(actualClock)) {
            this.actualClock = newClock;
            return true;
        }

        actualClock.setNext(newClock);
        return true;
    }

    /**Add the action to switch between Timers.*/
    private void setEndAction() {

        this.actionEndTimer = () -> {
            if (this.waitList.isEmpty()) {
                return;
            }

            if (!this.actualClock.hasEnded()) {
                this.actualClock.start();
                this.clockView.showView();
                return;
            }

            if (Objects.isNull(this.actualClock.getNext())) {
                return;
            }

//        Go to next Clock
            this.actualClock = actualClock.getNext();

            this.actualClock.start();
            this.waitList.poll();
//        Show the clock
            this.clockView.showView();
        };
    }

}
