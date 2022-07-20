package com.administer;

import javafx.scene.control.Label;

/**Class for save the labels and set the values*/
public class ListTimerQueue {
    final private Label minLabel;
    final private Label segLabel;

    public ListTimerQueue(Label minLabel, Label segLabel) {
        this.minLabel = minLabel;
        this.segLabel = segLabel;
    }

    public void setMinutes(Integer minutes) {
        this.minLabel.setText(minutes.toString());
    }

    public void setSeconds(Integer seconds) {
        this.segLabel.setText(seconds.toString());
    }

}
