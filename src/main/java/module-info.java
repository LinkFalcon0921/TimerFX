module TimerFx {
//    Necessary
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;

    opens com.start to javafx.graphics;
    opens com.controllers to javafx.fxml;
}