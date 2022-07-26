package com.util.timer;

import java.time.Duration;

public interface TimerSchedule {

    /**
     * Delay time as {@link Duration}.
     */
    Duration WAIT_TIME = Duration.ofMillis(1000l);
    /**Time before to start the timer as {@link Duration}.*/
    Duration DELAY_TIME = Duration.ofMillis(0l);

    /**Check if the timer end its work.*/
    boolean hasEnded();

    /**Play the timer.*/
    void start();

    /**Add to the "queue" the next timer.*/
    void setNext(TimerSchedule next);

    /**Get the next timer in the Queue*/
    TimerSchedule getNext();

}
