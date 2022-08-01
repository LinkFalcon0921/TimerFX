package com.TimerFX.util;

import javafx.scene.control.Label;

/**
 * Class for save the labels and set the values
 */
public class Labels {
    private final Label minLabel;
    private final Label segLabel;

    public Labels(Label minLabel, Label segLabel) {
        this.minLabel = minLabel;
        this.segLabel = segLabel;
    }


    public void setMinutes(Integer minutes) {
        String testMinutes = minutes.toString();
        this.minLabel.setText(getStringFormat(testMinutes));
    }

    private String getStringFormat(String testMinutes) {
        return String.format("%s%s",
                testMinutes.length() == 1 ? "0" : "",
                testMinutes
        );
    }

    public void setSeconds(Integer seconds) {
        this.segLabel.setText(seconds.toString());
    }

}
