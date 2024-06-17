module pl.oop.umcs.breakout {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.oop.umcs.breakout to javafx.fxml;
    exports pl.oop.umcs.breakout;
}