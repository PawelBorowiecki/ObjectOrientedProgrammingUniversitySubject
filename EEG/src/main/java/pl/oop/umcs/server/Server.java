package pl.oop.umcs.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    //Aby polaczyc sie z serwerem nalezy w terminalu wpisac telnet localhost nrPortu (tu:2137)
    private ServerSocket serverSocket;
    private List<ClientHandler> clients = new ArrayList<>();

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(2137);
    }

    public void listen() throws IOException {
        while (true){
            Socket socket = serverSocket.accept();
            ClientHandler client = new ClientHandler(socket);
            new Thread(client).start();
            clients.add(client);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.listen();
    }
}
