package com.counters;

import com.interfaces.ICounters;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Default clock timer
 */
public class Counter implements ICounters {

    private final AtomicInteger volatileSeg, volatileMin;

    public Counter(Integer min, Integer seg) {
        volatileMin = new AtomicInteger(min);
        volatileSeg = new AtomicInteger(seg);
    }

    @Override
    public Integer getSegs() {
        return this.volatileSeg.get();
    }

    @Override
    public Integer getMins() {
        return this.volatileMin.get();
    }

    /**
     * Decrease the time in 1 seconds.
     */
    public void reduce() {

        volatileSeg.updateAndGet(value -> {
            if (value-- < 0) {
                if(this.volatileMin.get() > 0){
                    this.volatileMin.decrementAndGet();
                    value = 59;
                }
            }

            return value;
        });

    }

    /**
     * Verify is the {@link Counter} ends.
     */
    public boolean isEnded() {
        return (getMins() == 0 && getSegs() == 0);
    }

    @Override
    public String toString() {
        return String.format("%s:%s", getMins(), getSegs());
    }
}
