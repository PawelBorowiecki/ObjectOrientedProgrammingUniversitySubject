module pl.oop.umcs.kolokwium22021 {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.oop.umcs.kolokwium22021 to javafx.fxml;
    exports pl.oop.umcs.kolokwium22021;
}