package com.TimerFX.builders;

import com.TimerFX.administer.AdminAction;
import com.TimerFX.worker.ClockActionTask;
import com.TimerFX.util.Labels;
import com.TimerFX.worker.Clockers;
import javafx.application.Platform;

import java.util.Objects;

public class ClockActionTaskBuilder {

    private static ClockActionTaskBuilder builder;

    private ClockActionTask actionTask;

    private ClockActionTaskBuilder(){
    }

    public static ClockActionTaskBuilder getDefaultBuilder() {
        if(Objects.isNull(builder)){
            builder = new ClockActionTaskBuilder();
        }

        return builder;
    }

    /**
     * Set the action as a {@link ClockActionTask} for the new Timer
     */
    public ClockActionTaskBuilder createDefaultActionTask(final Clockers clock, final Labels labels, final AdminAction action) {

        builder.actionTask = new ClockActionTask() {

            @Override
            public void toDoAction() {
                Platform.runLater(() -> {
                    getRunnableFxPlatform();
                });
            }

            /**Method that has the logic to do in the JavaFx Thread (Handle GUI only)*/
            @Override
            public void getRunnableFxPlatform() {
                //Action in the JavaFx Thread

                clock.reduceCount();
                labels.setMinutes(clock.getMinutes());
                labels.setSeconds(clock.getSeconds());

//                To string clock status.
                System.out.println(clock);
            }

            @Override
            public boolean isEnded() {
                return clock.isEnded();
            }

            @Override
            public void next() {
                action.next();
            }

        };

        return getDefaultBuilder();
    }

    public ClockActionTask get(){
        ClockActionTask taskToDo =  builder.actionTask;

//        Clean the builder variable
        builder.actionTask = null;

        return taskToDo;
    }

}
