package com.administer;

import com.worker.Clockers;

//TODO

/**
 * Class thread handle the clock time.
 */
@Deprecated
public class ColaCounters {

    /**
     * Default sleep time: 1 seconds
     */
//    public static final long SLEEP_TIME = 1000L;
    private Clockers clock;
    public ColaCounters() {
    }

    public void add(Integer min, Integer seg) {
        clock = new Clockers(min, seg);
    }

    @Override
    public String toString() {
        return clock.toString();
    }

}
