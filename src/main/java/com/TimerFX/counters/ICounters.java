package com.TimerFX.counters;

/** Allows handle the clock of the timer.*/
public interface ICounters {
    void reduce();

    boolean isEnded();

    Integer getMins();

    Integer getSegs();
}
