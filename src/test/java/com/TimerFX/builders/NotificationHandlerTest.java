package com.TimerFX.builders;

import com.TimerFX.notification.facade.NotificationHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotificationHandlerTest {

    private NotificationHandler handler;

    @Test
    public void setBuilder_getNotNull(){
        handler = NotificationHandler.getInstance();

        assertNotNull(handler);
    }

    @Test
    public void getAWindowsNotification(){
        handler = NotificationHandler.getInstance();

        handler.showAdviceNotification("Message passed");

    }

}