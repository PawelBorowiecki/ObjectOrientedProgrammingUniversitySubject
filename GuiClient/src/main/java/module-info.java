module oop.umcs.guiclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens oop.umcs.guiclient to javafx.fxml;
    exports oop.umcs.guiclient;
}