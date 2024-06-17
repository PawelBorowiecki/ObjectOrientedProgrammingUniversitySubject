package oop.umcs.guiclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Client client = new Client();
        new Thread(client).start();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        HelloController helloController = (HelloController)fxmlLoader.getController();
        helloController.bindWithClient(client);
        stage.setScene(scene);
        stage.show();
        helloController.reloadUserList(List.of("user1", "user2", "user3"));
        TextInputDialog textInputDialog = new TextInputDialog();
        Optional<String> login = textInputDialog.showAndWait();
        if(!login.isPresent()){
            System.exit(1);
        }
        client.send(login.get());
    }

    public static void main(String[] args) {
        launch();
    }
}