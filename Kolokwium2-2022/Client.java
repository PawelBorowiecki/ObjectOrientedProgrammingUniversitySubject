package pl.oop.umcs.kolokwium22022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client implements Runnable{
    private Socket socket;
    private BufferedReader reader;
    private HelloController controller;
    private Map<String, LocalTime> receivedWords = new HashMap<>();

    public Client(int port) throws IOException {
        this.socket = new Socket("localhost", port);
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        String message;
        try{
            while((message = reader.readLine()) != null){
                receivedWords.put(message, LocalTime.now());
                controller.getWord(LocalTime.now().toString() + " " + message);
                sortWords();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void setController(HelloController controller){
        this.controller = controller;
    }

    public int getAmountOfWords(){
        return this.receivedWords.size();
    }

    public void sortWords(){
        this.receivedWords.keySet().stream().sorted();
    }
}
