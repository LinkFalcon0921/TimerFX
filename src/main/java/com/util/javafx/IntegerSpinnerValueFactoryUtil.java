package com.util.javafx;

import javafx.scene.control.SpinnerValueFactory;

import static javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

public class IntegerSpinnerValueFactoryUtil {

    private IntegerSpinnerValueFactoryUtil(){

    }

    public static SpinnerValueFactory<Integer> getValueFactory(Integer low, Integer high){
        return new SpinnerValueFactory.IntegerSpinnerValueFactory(low,high);
    }

}
