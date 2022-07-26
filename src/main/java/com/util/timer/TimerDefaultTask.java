package com.util.timer;

import com.worker.ClockActionTask;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public final class TimerDefaultTask implements TimerSchedule {

    private final TimerTask actionTask;

    private final AtomicBoolean flagEnded;

    private boolean hasStarted;

    private TimerSchedule next;

    public TimerDefaultTask(ClockActionTask task, TimerDefaultTask next) {
        this.actionTask = task.getAsTimerTask();
        this.next = next;
        this.flagEnded = task.getFlagEnded();
    }

    public TimerDefaultTask(ClockActionTask task) {
        this(task, null);
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

    public void start() {
//        In case has been initiated
        if (this.hasStarted) {
            return;
        }

        this.hasStarted = true;
        ;
        new Timer()
                .scheduleAtFixedRate(this.actionTask, DELAY_TIME.toMillis(), WAIT_TIME.toMillis());

    }

    /**
     * Check if this clock has ended.
     */
    public boolean hasEnded() {
        return this.hasStarted && this.flagEnded.get();
    }

}
