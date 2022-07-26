package com.util.timer;

import com.worker.ClockActionTask;
import javafx.animation.Timeline;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

//TODO
public final class TimerTimelineFx implements TimerSchedule{

    private TimerTask actionTask;

    private final AtomicBoolean flagEnded;

    private boolean hasStarted;

    private TimerSchedule next;

    public TimerTimelineFx(AtomicBoolean flagEnded) {
        this.flagEnded = flagEnded;
    }

    public TimerTimelineFx(ClockActionTask actionTask, AtomicBoolean flagEnded) {
        this.actionTask = this.applyTask(actionTask);
        this.flagEnded = flagEnded;
    }

    private TimerTask applyTask(ClockActionTask action){

        return new TimerTask() {
            @Override
            public void run() {
                final AtomicBoolean flagEnd = flagEnded;
                action.toDoAction();

                flagEnd.set(true);
            }
        };

    }

    @Override
    public boolean hasEnded() {
        return this.flagEnded.get();
    }

    @Override
    public void start() {
        //        In case has been initiated
        if(this.hasStarted){
            return;
        }

//        TODO
        this.hasStarted = true;
    }

    @Override
    public void setNext(TimerSchedule next) {
        if(Objects.nonNull(this.next)){
            this.next.setNext(next);
            return;
        }

        this.next = next;
    }

    @Override
    public TimerSchedule getNext() {
        return this.next;
    }
}
