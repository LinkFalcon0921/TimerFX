package com.TimerFX.notification;

import javafx.util.Duration;

import java.awt.*;

abstract class NotificationImpl implements Notification{


    protected final TrayIcon notificationImpl;
//    protected PauseTransition transition;

    private final String title;
           private String message;

    public NotificationImpl(TrayIcon notification,String title, String message) {
        this.title = title;
        this.message = message;
        this.notificationImpl = notification;

        this.notificationImpl.addActionListener(e -> SystemTray.getSystemTray().remove(this.notificationImpl));

    }

    @Override
    public void show() {
        this.notificationImpl.displayMessage(title,message, TrayIcon.MessageType.INFO);
    }

    @Override
    public void setMessage(String message) {
        if (this.message.equals(message)) {
            return;
        }

        this.message = message;
    }

//    /**Set the time to show the {@link Notification} in milliseconds.*/
//    @Override
//    public void setTimeShown(Double millisTime) {
//    }

    /**Set the time to show the {@link Notification} in milliseconds.*/
    @Override
    public void setTimeShown(Duration millisTime) {
    }
}
