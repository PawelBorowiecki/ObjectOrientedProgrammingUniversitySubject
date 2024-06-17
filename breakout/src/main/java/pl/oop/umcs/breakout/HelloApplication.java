package pl.oop.umcs.breakout;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameCanvas gameCanvas = new GameCanvas();
        gameCanvas.initialize();
        GridPane gridPane = new GridPane();
        gridPane.add(gameCanvas, 0, 0);         //Dodajemy kanwe do siatki
        Scene scene = new Scene(gridPane, 640, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}