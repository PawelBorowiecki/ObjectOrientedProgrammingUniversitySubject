package pl.oop.umcs.client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class Client {
    public static void main(String[] args){
        try{
            Socket socket = new Socket("localhost", 2137);
            PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()), true);
            Files.lines(Path.of("tm00.csv")).forEach(printWriter::println);
            socket.close();     //konczymy dzialanie
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
}
