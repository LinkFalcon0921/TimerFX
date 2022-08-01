package com.TimerFX.start;

import com.TimerFX.notification.Notification;
import com.TimerFX.util.helpers.ImageGetter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Optional;

public class AppInit extends Application {

    public static final String CHRONOMETER_TITLE = "Timer Chronometer";
    public static final ImageGetter IMAGE_GETTER = ImageGetter.getInstance();
    public static final int STATUS_EXIT = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            String LOCATION = "/com/TimerFX/fmls/mainPage.fxml";
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(LOCATION)));
            Scene scene = new Scene(parent, 500, 500);
            stage.setScene(scene);
            Optional<Image> optionalImage = IMAGE_GETTER.getImageFX(Notification.IMAGE_ICON_NAME, ImageGetter.Extension.PNG_EXTENSION);
//            TODO: Consult
            optionalImage.ifPresent(image -> stage.getIcons().add(image));

            stage.setTitle(CHRONOMETER_TITLE);
            stage.show();

//            Close scene
            stage.setOnCloseRequest(windowEvent -> {
                stage.close();
                System.exit(STATUS_EXIT);
            });

            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }
}
