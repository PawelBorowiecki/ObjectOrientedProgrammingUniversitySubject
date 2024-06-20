package pl.oop.umcs.kolokwium22021;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    private final int port;
    private final DrawingServer drawingServer;

    public Server(int port, DrawingServer drawingServer) {
        this.port = port;
        this.drawingServer = drawingServer;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket, drawingServer)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
