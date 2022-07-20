package com.interfaces;

/** Allows handle the clock of the timer.*/
public interface ICounters {
    void reduce();

    boolean isEnded();

    Integer getMins();

    Integer getSegs();
}
