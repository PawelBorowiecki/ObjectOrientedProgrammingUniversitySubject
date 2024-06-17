package oop.umcs.guiclient;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class HelloController {
    @FXML
    private TextField textField;
    private TextArea outputArea;
    private ListView<String> userList;
    private Client client;

//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }

    @FXML
    private void send(){
        System.out.println("SEND");
        String message = textField.getText();
        outputArea.appendText(message + "\n");
        textField.clear();
        client.send("/broadcast " + message);
    }

    public void receive(String message){
        outputArea.appendText(message + "\n");
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void bindWithClient(Client client){
        this.setClient(client);                     //Wymiana informacji o sobie (linie ta i ponizsza) - asocjacja dwustronna
        client.setController(this);
    }

    public void reloadUserList(List<String> list){
        this.userList.getItems().clear();
        for(String username : list){
            this.userList.getItems().add(username);
        }
    }
}