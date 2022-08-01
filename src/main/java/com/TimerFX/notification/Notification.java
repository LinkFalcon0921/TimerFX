package com.TimerFX.notification;

import com.TimerFX.start.AppInit;
import javafx.util.Duration;

public interface Notification {

    String IMAGE_ICON_NAME = "time_out";

    String TITLE_APP_CALL_NAME = String.join("\u0020:\u0020", "From", AppInit.CHRONOMETER_TITLE);

    String ADVISE_MESSAGE = "Clock ended! ";

    Duration DEFAULT_TIME_PAUSE = Duration.seconds(2);

    void show();

//    void setTitle(String title);

    void setMessage(String message);

//    void setTimeShown(Double millisTime);

    void setTimeShown(Duration millisTime);
}
