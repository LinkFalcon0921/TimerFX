package com.TimerFX.worker;

import com.TimerFX.counters.Counter;
import com.TimerFX.counters.ICounters;

/**
 * Class that handle the {@link Counter} clock, as a manager.
 */
public class Clockers {
    ICounters counter;


    public Clockers(Integer min, Integer seg) {
        counter = new Counter(min, seg);
    }

    /** Reduce 1 second the time in the clock.*/
    public void reduceCount() {
        counter.reduce();
    }

    /**
     * Get the current seconds.
     */
    public int getSeconds() {
        return counter.getSegs();
    }

    /**
     * Get the current minutes.
     */
    public int getMinutes() {
        return counter.getMins();
    }

    /**
     * Check if the timer has ended.
     */
    public boolean isEnded() {
    return counter.isEnded();
    }

    @Override
    public String toString() {
        return counter.toString();
    }

}

