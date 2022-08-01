module TimerFx {
//    Necessary
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive java.desktop;

    opens com.TimerFX.start to javafx.graphics;
    opens com.TimerFX.controllers to javafx.fxml;

    opens com.TimerFX.notification to java.desktop;
}