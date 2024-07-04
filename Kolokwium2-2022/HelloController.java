package pl.oop.umcs.kolokwium22022;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    public TextField leftArea, middleArea, filterField;
    @FXML
    public ListView<String> wordList;
    private Client client;

//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }

    @FXML
    private Label totalWords = new Label("Total words: " + client.getAmountOfWords());

    @FXML
    public void filterResponse(){
        if(filterField.getText().isEmpty()){
            wordList.getItems();
        }else{
            wordList.scrollTo(filterField.getText());
        }
    }

    public void bindWithClient(Client client){
        this.client = client;
        client.setController(this);
    }

    public void getWord(String word){
        wordList.setAccessibleText(word);
    }

}