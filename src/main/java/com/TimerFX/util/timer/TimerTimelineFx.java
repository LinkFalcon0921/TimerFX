package com.TimerFX.util.timer;

import com.TimerFX.worker.ClockActionTask;

import java.util.Objects;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

//TODO
public final class TimerTimelineFx extends TimerScheduleImpl{

    public TimerTimelineFx() {
        super(new AtomicBoolean());
    }

    private TimerTask applyTask(ClockActionTask action){

        return new TimerTask() {
            @Override
            public void run() {
                action.toDoAction();

                flagEnded.set(true);
            }
        };

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

}
