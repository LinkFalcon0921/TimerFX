package com.TimerFX.util.timer;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;


abstract class TimerScheduleImpl implements TimerSchedule {

    protected final AtomicBoolean flagEnded;

    protected volatile boolean hasStarted;

    protected TimerSchedule next;

    public TimerScheduleImpl(AtomicBoolean flagEnded) {
        this.flagEnded = flagEnded;
    }

    public void setNext(TimerSchedule next) {
        if (Objects.nonNull(this.next)) {
            this.next.setNext(next);
            return;
        }

        this.next = next;
    }

    public TimerSchedule getNext() {
        return this.next;
    }

    /**
     * Check if this clock has ended.
     */
    public boolean hasEnded() {
        return this.hasStarted && this.flagEnded.get();
    }

}
