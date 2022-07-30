package com.TimerFX.util.javafx;

import javafx.scene.control.SpinnerValueFactory;

public class IntegerSpinnerValueFactoryUtil {

    private IntegerSpinnerValueFactoryUtil(){

    }

    public static SpinnerValueFactory<Integer> getValueFactory(Integer low, Integer high){
        return new SpinnerValueFactory.IntegerSpinnerValueFactory(low,high);
    }

}
