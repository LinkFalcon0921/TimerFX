package com.TimerFX.util.timer;

import com.TimerFX.worker.ClockActionTask;

import java.util.Timer;
import java.util.TimerTask;

public final class TimerDefaultTask extends TimerScheduleImpl {

    private final TimerTask actionTask;

    public TimerDefaultTask(ClockActionTask task) {
        super(task.getFlagEnded());
        this.actionTask = task.getAsTimerTask();
    }

    public TimerDefaultTask(ClockActionTask task, TimerDefaultTask next) {
        this(task);
        this.next = next;
    }


    public void start() {
//        In case has been initiated
        if (this.hasStarted) {
            return;
        }

        this.hasStarted = true;

        new Timer()
                .scheduleAtFixedRate(this.actionTask, DELAY_TIME.toMillis(), WAIT_TIME.toMillis());

    }

}
