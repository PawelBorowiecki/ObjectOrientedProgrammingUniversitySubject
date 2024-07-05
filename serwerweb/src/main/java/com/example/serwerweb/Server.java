package com.example.serwerweb;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.w3c.dom.css.RGBColor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

import static java.sql.Types.NULL;

@Controller
public class Server{
    private ServerSocket serverSocket;
    private BufferedReader reader;
    private static final String password = "LiscDebu";
    List<Client> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.createDatabase();
        server.showGraphicsFromDb();
        server.listen();
    }

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(2137);
        reader = new BufferedReader(new InputStreamReader(serverSocket.accept().getInputStream()));
    }

    public void listen() throws IOException {
        while (true){
            Socket socket = serverSocket.accept();
            Client client = new Client(socket);
            new Thread((Runnable) client).start();
            String input = reader.readLine();
            if(!input.equals(password)){
                socket.close();
            }else{
                clients.add(client);
                if(reader.readLine().substring(0,3).equals("ban")){
                    client.setUserToken(NULL);
                    try(Connection connection = DriverManager.getConnection("/tmp/Kolokwium2/serwerweb/pixels.db")){
                        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM entry WHERE token=" + client.getUserToken());
                        int affectedRows = preparedStatement.executeUpdate();
                        preparedStatement.close();
                        connection.close();
                        System.out.println("Usunieto " + affectedRows + " rekordow.");
                    }catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                    showGraphicsFromDb();
                }
            }
        }
    }

    @PostMapping("/register")
    public String getToken(Client client){
        int token = client.getUserToken();
        StringBuilder message = new StringBuilder();
        message.append("{\n \"id\": ");
        message.append(client.getUserToken());
        message.append(",\n \"create time\": ");
        message.append(LocalTime.now().toString());
        message.append(",\n}");
        return message.toString();
    }

    @GetMapping("/tokens")
    public List<String> getActiveTokens(){
        List<String> activeTokens = new ArrayList<>();
        for(Client client : clients){
            LocalTime currTime = client.getCreationToken().minusMinutes(5);
            if(!currTime.isAfter(LocalTime.now())){
                activeTokens.add(String.format("{\n \"token\": %d,\n \"create time\": %s,\n \"is active\": \"yes\"}", client.getUserToken(), client.getCreationToken().toString()));
            }
        }
        return activeTokens;
    }

    @GetMapping("/image")
    public void generateRGB(){
        BufferedImage image = new BufferedImage(512,512, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,512,512);
        g2d.drawRect(0,0,512,512);
    }

    @PostMapping("/pixel")
    public void setPixelColor(@RequestBody int userToken, int xCoordinate, int yCoordinate, RGBColor color) throws IOException {
        for(Client client : clients){
            if(client.getUserToken() == userToken){
                if(client.getCreationToken().minusMinutes(5).isAfter(LocalTime.now())){
                    System.out.println("302 Forbidden");
                }else if(xCoordinate < 0 || xCoordinate > 512 || yCoordinate < 0 || yCoordinate > 512){
                    System.out.println("400 Bad Request");
                }else{
                    client.setPixel(xCoordinate,yCoordinate,color.toString());
                    try {
                        String insertSQL = "INSERT INTO entry VALUES(" + userToken + "," + xCoordinate + "," + yCoordinate + "," + color.toString() + "," + client.getCreationToken() + ")";
                        Connection connection = DriverManager.getConnection("/tmp/Kolokwium2/serwerweb/pixels.db");
                        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                        preparedStatement.setInt(1, userToken);
                        preparedStatement.setInt(2, xCoordinate);
                        preparedStatement.setInt(3, yCoordinate);
                        preparedStatement.setString(4, color.toString());
                        preparedStatement.execute();
                        preparedStatement.close();
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("200 OK");
                }
            }
        }
    }

    private static String encodeBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        String base64Image = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        return base64Image;
    }

    private void createDatabase(){
        String url = "jdbc:sqlite:/tmp/Kolokwium2/serwerweb/pixels.db";
        String createTableSQL = "CREATE TABLE IF NOT EXISTS entry (token INTEGER NOT NULL, x INTEGER NOT NULL, y INTEGER NOT NULL, color TEXT NOT NULL, timestamp TEXT NOT NULL);";
        try(Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement()){
            statement.execute(createTableSQL);
            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private void showGraphicsFromDb(){
        String query = "SELECT token, x, y, color FROM entry ORDER BY timestamp";
        try(Connection connection = DriverManager.getConnection("/tmp/Kolokwium2/serwerweb/pixels.db");
            Statement statement = connection.createStatement()){
            statement.execute(query);
            statement.close();
            connection.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
