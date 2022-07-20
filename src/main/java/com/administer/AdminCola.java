package com.administer;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.LinkedList;
import java.util.Queue;

//TODO USE in the initilize controller

/**
 * <i>SINGLETON CLASS<i/>, that handle the interaction with the view components and communicate with the logical part.
 */
public class AdminCola extends Thread {

    /**
     * Timer Queue handler
     */
    private final Queue<ColaCounters> cola;
    /**
     * Labels handler
     */
    private final ListTimerQueue labels;

    /**
     * Waiting list handler
     */
    final WaitList waitList;

    public AdminCola( Label minLabel, Label segLabel) {
        cola = new LinkedList<>();
        labels = new ListTimerQueue(minLabel, segLabel);
        waitList = new WaitList();
    }

    public void add(Integer min, Integer seg) {

        ColaCounters timerInQueue = new ColaCounters(labels);

//        Add the time in the new timer
        timerInQueue.add(min, seg);
//        Add to the queue
        cola.add(timerInQueue);
//      Add in the waiting list
        waitList.add(timerInQueue.toString());

        if (!isAlive()) {
            start();
        }
    }

    public void started() {
        try {
            waitList.poll();
            cola.peek().start();

        } catch (NullPointerException e) {

        }

    }

    @Override
    public void run() {
        while (true) {
            started();
        }
    }

}
