package com.builders;

import com.worker.Clockers;


public class ClockerBuilder {
    private static ClockerBuilder builder;

    private Clockers clockTimer;

    private ClockerBuilder(){}

    public static ClockerBuilder getDefaultBuilder(){
        if (builder == null) {
            builder = new ClockerBuilder();
        }

        return builder;
    }

    /**Create a new clock as a <i>{@link Clockers} class</i> using the given time.*/
    public ClockerBuilder createDefaultClock(Integer min, Integer seg){
        builder.clockTimer = new Clockers(min,seg);

        return builder;
    }

    /** Get the clock built inside the builder*/
    public Clockers get(){
        Clockers clockMade = builder.clockTimer;
//      Clean the builder clock
        builder.clockTimer = null;

        return clockMade;
    }
}
