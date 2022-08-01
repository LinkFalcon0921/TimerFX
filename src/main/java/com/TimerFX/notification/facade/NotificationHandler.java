package com.TimerFX.notification.facade;

import com.TimerFX.notification.Notification;
import com.TimerFX.notification.WindowsNotification;
import com.TimerFX.start.AppInit;
import com.TimerFX.util.helpers.ImageGetter;
import javafx.util.Duration;

import java.awt.*;
import java.util.Objects;
import java.util.Optional;

import static com.TimerFX.util.helpers.ImageGetter.Extension;

public class NotificationHandler {

    private static final String NOT_POSSIBLE_GET_A_TRAY_ICON_MESSAGE = "It was not possible get a TrayIcon";
    private static final String TASK_DONE_TITLE_TEXT = "Task Done! \n";
    private static NotificationHandler builder;
    private static TrayIconBuilder trayIconBuilder;
    private Notification adviseNotification;
//    TODO : instantiate
    private Notification warningNotification;

    private NotificationHandler() {
        trayIconBuilder = TrayIconBuilder.getInstance();
    }

    public static NotificationHandler getInstance() {
        if (Objects.isNull(builder)) {
            builder = new NotificationHandler();
        }

        return builder;
    }

    public void showAdviceNotification(String adviseMessage) {
        builder.showAdviceNotification(adviseMessage, Notification.DEFAULT_TIME_PAUSE);
    }

    public void showAdviceNotification(String adviseMessage, Duration durationShown) {
        final String finalAdviseMessage = builder.joinMessage(Notification.ADVISE_MESSAGE,adviseMessage);

        if (Objects.isNull(builder.adviseNotification)) {

            TrayIcon trayIcon = builder.getTrayIcon(Notification.IMAGE_ICON_NAME, Extension.PNG_EXTENSION);

            String titleAppCallName = TASK_DONE_TITLE_TEXT.concat(Notification.TITLE_APP_CALL_NAME);
            builder.adviseNotification = new WindowsNotification(
                    trayIcon,
                    titleAppCallName,
                    finalAdviseMessage);
        }

        builder.adviseNotification.setMessage(finalAdviseMessage);
        builder.adviseNotification.setTimeShown(durationShown);

        builder.adviseNotification.show();
    }

    /**Assable the Message to the Notification*/
    private String joinMessage(String... adviseMessage) {
        return String.join("-->", adviseMessage);
    }

    private TrayIcon getTrayIcon(String imageName, Extension imageExtension) {
        Optional<TrayIcon> defaultOptionalTrayIcon = trayIconBuilder.getDefaultTrayIcon(imageName, imageExtension);

        if (defaultOptionalTrayIcon.isEmpty()) {
            throw new NullPointerException(NOT_POSSIBLE_GET_A_TRAY_ICON_MESSAGE);
        }

        return defaultOptionalTrayIcon.get();
    }

}

/**
 * Class {@link TrayIcon} Builder
 */
class TrayIconBuilder {


    private static SystemTray systemTray;
    private static TrayIconBuilder builder;
    private static ImageGetter imageGetter;

    private TrayIconBuilder() {
        imageGetter = ImageGetter.getInstance();
        systemTray = SystemTray.getSystemTray();
    }

    public static TrayIconBuilder getInstance() {
        if (builder == null) {
            builder = new TrayIconBuilder();
        }

        return builder;
    }

    /**
     * Get a {@link TrayIcon} with the specific {@link Image} taking the given <i>ImageName</i> and the @{@link Extension} as the file has been saved.<br/>
     */
    public Optional<TrayIcon> getDefaultTrayIcon(final String imageName, final Extension imageExtension) {
        if (!SystemTray.isSupported()
//                Not valid outputs
                || !imageName.isBlank() && Objects.isNull(imageExtension)) {
            return Optional.empty();
        }

        Optional<Image> optionalImage = imageGetter.getImage(imageName, imageExtension);

        if (optionalImage.isEmpty()) {
            return Optional.empty();
        }

        TrayIcon trayIcon = new TrayIcon(optionalImage.get());

        trayIcon.setImageAutoSize(true);

        trayIcon.setToolTip(AppInit.CHRONOMETER_TITLE);

//        TODO
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            return Optional.empty();
        }

        return Optional.of(trayIcon);
    }


}
