package com.administer;

import com.worker.Clockers;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ForkJoinTask;

//TODO

/**
 * Class thread handle the clock action.
 */
public class ColaCounters extends Timer {

    /**
     * Default sleep time: 1 seconds
     */
    public static final long SLEEP_TIME = 1000L;
    private Clockers clock;
    private final ListTimerQueue elements;

    public ColaCounters(ListTimerQueue coll) {
        elements = coll;
    }

    public void add(Integer min, Integer seg) {
        clock = new Clockers(min, seg);
    }

    public void start(){
        this.schedule(getTask(),SLEEP_TIME);
    }

    private TimerTask getTask() {

        return new TimerTask() {
            @Override
            public void run() {
                if (clock != null) {
                    while (!clock.isEnded()) {

                        clock.reduceCount();
                        elements.setMinutes(clock.getMinutes());
                        elements.setSeconds(clock.getSeconds());

                        try {
                            Thread.sleep(SLEEP_TIME);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        };

    }

    @Override
    public String toString() {
        return clock.toString();
    }

}
