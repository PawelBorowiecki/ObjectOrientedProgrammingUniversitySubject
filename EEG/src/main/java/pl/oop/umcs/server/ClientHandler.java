package pl.oop.umcs.server;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class ClientHandler implements Runnable{
    private BufferedReader reader;
    private List<List<Float>> data = new ArrayList<>();     //Nie mozemy tworzyc kolekcji typow prostych - musi byc typ obiektowy

    public ClientHandler(Socket socket) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private void parseMessage(String message){
        List<Float> lineData = Arrays.stream(message.split(",")).map(Float::parseFloat).toList();   //Ta liste floatow mozemy juz zapisac do data
        data.add(lineData);
    }

    public void generate(int index) throws IOException {        //generujemy obraz w formacie base64
        List<Float> dataLine = data.get(index);
        BufferedImage image = new BufferedImage(dataLine.size(), 140, BufferedImage.TYPE_INT_ARGB);
        for(int i = 0; i < dataLine.size(); i++){
            int y0 = image.getHeight() / 2;
            int y = (int) (-dataLine.get(i) + y0);
            image.setRGB(i,y,0xffff0000);   //ARGB - A oznacza przezroczystosc(gdy jest 0 to jest przezroczyste)
        }
        //ImageIO.write(image, "png", new File("image.png")); //Zapisujemy obrazek testowo
        File file = new File("data.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.println(encodeBase64(image));
        writer.close();
        System.out.println(encodeBase64(image));
    }

    private static String encodeBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        String base64Image = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        return base64Image;
    }

    @Override
    public void run() {
        String message;
        try{
            while ((message = reader.readLine()) != null && !message.equals("Bye")){
                parseMessage(message);
                generate(data.size() - 1);
            }
        }catch(IOException e){
            throw new RuntimeException();
        }
    }
}
