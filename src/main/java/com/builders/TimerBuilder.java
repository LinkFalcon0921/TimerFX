package com.builders;

import com.administer.AdminAction;
import com.util.Labels;
import com.util.timer.TimerDefaultTask;
import com.worker.ClockActionTask;
import com.worker.Clockers;

import java.util.Timer;


public class TimerBuilder {
    private static TimerBuilder builder;

    private final ClockActionTaskBuilder clockTaskBuilder;

    private TimerDefaultTask clockTimer;

    private TimerBuilder() {
        this.clockTaskBuilder = ClockActionTaskBuilder.getDefaultBuilder();
    }

    public static TimerBuilder getDefaultBuilder() {
        if (builder == null) {
            builder = new TimerBuilder();
        }

        return builder;
    }

    /**
     * Create a new timer as a <i>{@link Timer} class</i> using the given time.
     */
    public TimerBuilder createDefaultTimer(Labels labels, Clockers clock, AdminAction action) {
        ClockActionTask task = builder.clockTaskBuilder.createDefaultActionTask(clock, labels, action).get();

        builder.clockTimer = new TimerDefaultTask(task);

        return getDefaultBuilder();
    }

    /**
     * Get the timer built inside the builder
     */
    public TimerDefaultTask get() {
        TimerDefaultTask clockMade = builder.clockTimer;

//      Clean the builder clock
        builder.clockTimer = null;

        return clockMade;
    }


    /*Private methods*/


}
