package pl.oop.umcs.kolokwium22022;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Client client = new Client(5000);
        new Thread(client).start();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        HelloController controller = (HelloController)fxmlLoader.getController();
        controller.bindWithClient(client);
        GridPane gridPane = new GridPane();
        gridPane.addRow(0, controller.leftArea, controller.middleArea, controller.filterField);
        gridPane.addRow(0, controller.wordList, controller.wordList, controller.wordList);
        Scene scene = new Scene(gridPane, 900, 600);
        stage.setTitle("Welcome to word's world!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}