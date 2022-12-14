package com.TimerFX.worker;

import java.util.Objects;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ClockActionTask {

    protected TimerTask task;

    protected final AtomicBoolean flagEnded;

    public ClockActionTask(){
        this.flagEnded = new AtomicBoolean();
    }

    /**
     * Get the instance as a {@link TimerTask} Object.
     */

    public final TimerTask getAsTimerTask() {

        if(Objects.isNull(this.task)){
            final ClockActionTask actionClock = this;

//        Set the timer task
            this.task = new TimerTask() {

                @Override
                public void run() {
                    if(actionClock.isEnded()){
//                        Apply the flag ended
                        actionClock.flagEnded.set(true);
//                        Cancel the TimerTask
//                        Do the next Action.
                        actionClock.next();
                        this.cancel();
                        return;
                    }

                    actionClock.toDoAction();
                }
            };
        }

        return this.task;
    }

    public AtomicBoolean getFlagEnded(){
        return this.flagEnded;
    }

    /**Action that set the GUI handle.*/
    public abstract void toDoAction();

    protected abstract void getRunnableFxPlatform();

    /**Verify if the given condition apply.*/
    public abstract boolean isEnded();

    /**Do the final action before end the task.<br/>This action should run another timer.*/
    public abstract void next();
}
