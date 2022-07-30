module TimerFx {
//    Necessary
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;

    opens com.TimerFX.start to javafx.graphics;
    opens com.TimerFX.controllers to javafx.fxml;
}