package pl.oop.umcs.kolokwium22021;

import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    private final DrawingServer drawingServer;

    public ClientHandler(Socket clientSocket, DrawingServer drawingServer) {
        this.clientSocket = clientSocket;
        this.drawingServer = drawingServer;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String message;
            while ((message = in.readLine()) != null) {
                if (message.matches("^[0-9A-Fa-f]{6}$")) {
                    drawingServer.setColor(Color.web("#" + message));
                } else if (message.matches("^\\d+\\.\\d+ \\d+\\.\\d+ \\d+\\.\\d+ \\d+\\.\\d+$")) {
                    String[] parts = message.split(" ");
                    double x1 = Double.parseDouble(parts[0]);
                    double y1 = Double.parseDouble(parts[1]);
                    double x2 = Double.parseDouble(parts[2]);
                    double y2 = Double.parseDouble(parts[3]);
                    Line line = new Line(x1, y1, x2, y2, drawingServer.getCurrentColor());
                    drawingServer.addLine(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
