package com.counters;

import com.interfaces.ICounters;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Default clock timer
 */
public class Counter implements ICounters {

    private AtomicInteger volatileSeg, volatileMin;

    public Counter(Integer min, Integer seg) {
        volatileMin = new AtomicInteger(min);
        volatileSeg = new AtomicInteger(seg);
    }

    @Override
    public Integer getSegs() {
        return volatileSeg.get();
    }

    @Override
    public Integer getMins() {
        return volatileMin.get();
    }

    /**
     * Decrease the time in 1 seconds.
     */
    public void reduce() {

        volatileSeg.updateAndGet(value -> {
            if (value-- < 0) {
                volatileMin.decrementAndGet();
                value = 59;
            }
            return value;
        });

    }

/**Verify is the {@link Counter} ends.*/
    public boolean isEnded() {
        return (getMins() == 0 && getMins() == 0);
    }

    @Override
    public String toString() {
        return String.format("%s:%s", getMins(), getSegs());
    }
}
